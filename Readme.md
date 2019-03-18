# 第二章 建立與銷毀物件的方法

## item1 - 使用 static factory method 取代 constructors

一般來說 Java 主要以 constructors 來建立新的 instance 但是其實 Java 可以在 class 中提供  static factory method 來建立 instance

### 使用  static factory method 建立 instance 有幾種好處

* 可以有名稱 

有名稱可以讓使用物件的人容易理解如何建立此物件 instance 如: Integer.valueOf()

* 使用 static factory method 不一定要建立新的物件

可以直接回傳已建立過的物件 如: 
```java
public final class Boolean implements java.io.Serializable,
                                      Comparable<Boolean>
{
    /**
     * The {@code Boolean} object corresponding to the primitive
     * value {@code true}.
     */
    public static final Boolean TRUE = new Boolean(true);

    /**
     * The {@code Boolean} object corresponding to the primitive
     * value {@code false}.
     */
    public static final Boolean FALSE = new Boolean(false);
    
    public static Boolean valueOf(boolean b) {
        return (b ? TRUE : FALSE);
    }

}
```

*  可以在回傳回傳型別的子型別 - 參考 java.util.Collections 
*  回傳的物件可以變更 - EnumSet 在 enum 大小為 64 以下回傳 RegularEnumSet instance 以上回傳 JumboEnumSet
*  可以回傳一個 interface 不一定要先實作物件 - 參考 ServiceLoader.java  load() 方法 https://stackoverflow.com/questions/53240626/what-does-static-factories-returned-object-need-not-exist-mean

### 使用  static factory method 建立 instance 有幾種壞處

* 如果 class 只有 private constructors 不能被繼承
* static factory method 單由文件很難判斷建議以以下幾種命名法則命名

    * from—A type-conversion method that takes a single parameter and returns acorresponding instance of this type, for example:Date d = Date.from(instant);
    * of—An aggregation method that takes multiple parameters and returns an in-stance of this type that incorporates them, for example:Set<Rank> faceCards = EnumSet.of(JACK, QUEEN, KING);
    * valueOf—A more verbose alternative to from and of, for example:BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);
    * instance  or  getInstance—Returns  an  instance  that  is  described  by  its  pa-rameters (if any) but cannot be said to have the same value, for example:StackWalker luke = StackWalker.getInstance(options);
    * create  or  newInstance—Like instance  or  getInstance,  except  that  themethod guarantees that each call returns a new instance, for example:Object newArray = Array.newInstance(classObject, arrayLen);
    * getType—Like getInstance, but used if the factory method is in a differentclass. Type is the type of object returned by the factory method, for example:FileStore fs = Files.getFileStore(path);
    * newType—Like newInstance, but used if the factory method is in a differentclass. Type is the type of object returned by the factory method, for example:BufferedReader br = Files.newBufferedReader(path);
    * type—A concise alternative to getType and newType, for example:List<Complaint> litany = Collections.list(legacyLitany);

## Item2 考慮使用 build pattern 當 constructor 參數很多時

## Item3 建立 singleton 物件的技巧 使用 private constructor 或 enum type

 * public static final field 的方式
 
 最簡單, 但是 eager-loading
 
 * public static factory method 的方式
 
 可以有彈性的建立物件, 可以用範型, 可有用 java8 的 supplier 傳入 lambda(見註釋1)
 但是有一些序列化的問題要解決
  
 註釋1: supplier=() -> T Elvis::instance 意思是 Elvis.instance() 產生一個 Elvis 物件可以用 High order function 之特性傳入supplier functional interface
 * enum singleton
 
 比較好的方法,但是不能繼承其他 class 不過可以實作 interface 

## Item4 用 private constructor 確保物件不能被 實例化(instantiated)

有些 class 如 util 類, 包含了很多 static method, 這些 class 其實不需要被實例化, 
因此建議以 private constructor 確保它不能被實例化.   

## Item5 使用 dependency injection 來注入物件
以下是拼字檢查的物件,相依於 Lexicon 物件, 而 Lexicon 可能有很多不同實作如 EnglishLexicon, ChineseLexicon , 
可用 dependency injection 的方式傳入 factory pattern 之 FactoryClass 以在 constructor 產生不同實作的 Lexicon.
另外於 Java8 可使用 supplier functional interface 來限制 FactoryClass 的 type 如: Supplier<? extends Lexicon> lexiconFactory
```java
public class SpellChecker {
    private final Lexicon dictionary;

    public SpellChecker(Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }

    public boolean isValid(String word) {
    }

    public List<String> suggestions(String typo) {
    }
}
```
## Item 6 避免建立不必要的物件

在開發時盡量減少建立不必要物件如下使用 String.matches 時會建立 一個 Pattern 物件,
因此如果能先將 Pattern 建立出來則不需要每次呼叫 isRomanNumeral 時都建立 Pattern 物件.
當使用 Boxed Primitive 之物件 如 Long 時每次重新 assign 一個新 value 都會重新產生一個 Long 物件需要注意.
```java
//bad
class RomanNumerals {
    static boolean isRomanNumeral(String s) {
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})" 
                + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }
}
//good
class RomanNumerals {
    private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})" + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    static boolean isRomanNumeral(String s) {
        return ROMAN.matcher(s).matches();
    }
}
```
## Item7 消除未使用的 物件 references
在 Java 中 memory leak 通常出現於 *未預期的物件保留(unintentional object retentions)* , 如於 Stack 陣列中維護物件而沒有 release 未使用之陣列物件.
為了保持程式的可讀性將 GC 交由 JVM 對於消除未使用的 物件 references 需要針對例外的狀況消除常見的情形有
* 維護陣列的物件參考
* 維護 cache 的物件
* Listeners 與 其他 Callbacks 物件

可試著使用 WeakHashMap 去自動消除未使用的 key 所對應的 value, 
Remember  that  WeakHashMap  is  useful  only  if  the  desired  lifetime  of cache entries is determined by external references to the key, not the value

## Item8 避免使用 finalizers 與 cleaners
finalizers 會在 gc 後被執行, 但是 java gc 時機是由 JVM 演算法決定的因此不能保證即時性, 
並且使用它會影響 gc 效能（about 50times slower to create and destroy objects with finalizers）, 
cleaners 快一點約 5 倍, 除非要做資源釋放的保險否則不要使用.

> java 9 後 finalizers 被棄用改為 cleaners
 
 
 可使用 finalizers 與 cleaners 的情況
 * 作為資源釋放的保險, 並且這些資源可以接受非即時性的釋放 如： FileInputStream, FileOutputStream, ThreadPoolExecutor, and java.sql.Connection
 * native  peers(一個包含 native methods 的物件)的資源釋放保險, 如效能與即時性可以妥協的話, 如不能妥協請使用 AutoCloseable 實作 close method
 
## Item9 使用 try(resource) 而不使用 try{} finally{}
 
使用 try{} finally{} 用於 resource 的關閉不只程式碼比較醜, 還可能發生第一個 exception 被偵測後而執行 close method 後發生第二個 exception 時丟出 exception Stack 的狀況造成 debug 困難, 因第一個 exception 沒印出 exception Stack message.
比如說當讀檔案時, 硬碟壞了發生 exception 然後程式碼直接跳到 close method 執行然後發生第二個 exception 則 exception Stack 顯示的 message 會是 close method 的 message.  

在 Java 7 後引入了 try(resource) 方法只要 resource 有 implement AutoCloseable interface 在 try 中可以放入 resource 這種方式不只程式碼比較漂亮, 而且還能夠發生 exception 時印出第一個 exception Stack.
     
# 第三章 Java 物件的通用方法
本章介紹物件通用方法 equals, hashCode, toString, clone 如何撰寫 finalize前面介紹過了.

## Item 10
 
 





