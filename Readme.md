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

## Item 10 當 overriding equals() 時須遵守的規範

```java
//Object equals method
public boolean equals(Object obj) {
     return (this == obj);
 }

```

當以下情況不要 override 
* 每個 instance 本質上是唯一的情況 

如 Thread class 表示它所生成的 instance 實際上是一個 thread 對於其 value 是較不關心的, 因此原本的 Object.equals() 之行為就足夠了.
* class 不需要提供**邏輯上相等**的判斷

如 java.util.regex.Pattern class 可以 overridden equals 去判斷兩個 Pattern 是否使用一樣的 regular expression ,
但是沒有人會想去判斷兩個 Pattern 是否一樣所以 equals 不需要 override
* superclass 已經 override 了 equals 並且不需要在提供額外的行為了

如 Set 繼承了 AbstractSet 的 equals, List 繼承了 AbstractList, Map 繼承了 AbstractMap 其 equals 已經提供足夠的行為了, 不須額外實作.
* class 是 private 或是 protected 並且你確定 equals 不會被呼叫

你可以直接 override equals 方法確保別人不會呼叫它.
```java
@Override public boolean equals(Object o) { 
    throw new AssertionError(); // Method is never called
}
```
實作 equals 需要遵守的幾個約束 
* Reflexive: 任何 x 物件的 reference value 非 null 時, x.equals(x) return true
* Symmetric: 任何 x, y物件的 reference value 非 null 時 x.equals(y) return true 並且 y.equals(x) return true.
* Transitive: 任何 x, y, z物件的 reference value 非 null , 如 x.equals(y) return true 並 y.equals(z) return true,
 則 x.equals(z) 須 return true.
* Consistent: 任何 x, y物件的 reference value 非 null, 多次的 x.equals(y) 呼叫需為不變的 true 或 false equals.
* non-nullity: 任何 x 物件的 reference value 非 null, x.equals(null) 需 return false.

Java 內建的 java.sql.Timestamp 與 java.util.Date 違反 Symmetric 不能混和用在 Collection 

java.net.URL 違反了 Consistent. equals 會根據host IP 改變結果, 同個 URL 物件如果其 host 的 IP 改變了 equals 結果可能會變

如果要 null check 時不需要 check null
```java
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
    }
```
直接使用 instanceof 如果 Object 是 null instanceof 一定回傳 false [JLS 15.20.2](https://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.20.2)
```java
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MyType)) 
            return false;
        MyType mt = (MyType) o;
    }
```

equals 的寫法

1. 使用 == 來確認 object 的  reference value: if true 直接 return true, 省了後面的 compare 運算
2. 使用 instanceof 來確認參數是正確的型別: if not return false,
3. 將參數的型別 cast 成正確的型別: cast 因為在 instanceof 後面所以可以確保能夠 cast 成功
4. 對於傳入的物件之每個重要欄位進行比較: 如果這些欄位都通過測試則回傳 true 否則回傳 false. (如 步驟2 的參數是 interface 則使用 method 進行 compare, 如果是 class 可以由 欄位的
 accessibility 決定是否要直接使用呼叫欄位)

primitive fields 可直接用 == 比較, 如為 float double 用 Float Double compare() method 比較, 用Float.equals()會有 autoboxing 的問題會造成效能低落, 
如為物件用 equals 比較, 如為 array 用 Arrays.equals(). 

有些物件 reference value 可能是 null 可以用 Objects.equals(Object,Object) 來避免 NullPointException
```java
public final class Objects {
    public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
```
寫完 equals 請寫 unit test 來測 Symmetric Transitive Consistent 三種特性  (Consistent 如何測？)

最後幾點 equals 的注意事項
* override equals 時也要 override hashCode
* 別自作聰明: 如果單純的比較欄位的相等已達到 equals 的約束並不會太困難. 但如果你考慮太多以去進行比較往往會造成錯誤, 
如: File class 不需要去比較 file 的 symbolic links 是否指向同一個 file .
* 不要使用在 equals(Object object) 放入其他的型別如以下, 如果這樣它會變成 overloads, 如果你試著在上面加上 @Override 會不能編譯, 因為 super class 沒有這個 method
```java
public boolean equals(MyClass o) { }
```
可以嘗試使用 autovalue 自動生成 equals, hashCode (類似 lombok).

如果不需要盡量不要 override equals method, 如 override 確保它所有重要的欄位都要被比較, 並確保其符合上面提到的五個約束.

## Item 11 當 override equals 時也要 override hashCode 

hashCode 的約束
* 如果被 equals 比較的值沒有變化那麼, 當 hashCode 被呼叫時需要一直保持一樣的回傳值
* 如果兩個物件的被 equals 判斷為 true, 這兩物件的 hashCode 須為同樣的值
* 如果兩個物件的被 equals 判斷為 false, 這兩物件的 hashCode 不一定不同. 然而須注意的是如果 unequal 的值其 hashCode 不同, 可以提高 hash tables 的效能.
 
 以下方法無違反約束, 但是會造成每次 hashCode 都回傳一樣的值, 以至於 hash table 的物件放到 Linked List 當要尋找同樣 key 的物件 reference 時會到 Linked List 找下去造成效能不好.
 ```java
// The worst possible legal hashCode implementation - never use!
@Override public int hashCode() { return 42; }
```

**hashCode 的作法**
1. int result, code; 
2. 計算從有意義的欄位計算 code
    1. 如欄位是 primitive type, 使用 Type.hashCode(f), 如 Integer.hashCode(f)
    2. 如欄位是 object 並且 class 的 equals 是由此 object 的 equals 方法實作的, 呼叫 Type.hashCode(object), 如果 object 是 null 則回傳 0.
    3. 如果欄位是 Array, 並且裡面的值都是有意義的用 Array.hashCode , 如並非全部有意義的 forEach 每個值 然後 計算  result = 31 * result + code, 
    如無意義的值 code 請設為一個非0的常數;
3. 依照以上作法 result = 31 * result + c; 計算結果 // 使用 31 在 jvm 可以使用此方法最佳化 31*i == (i<<5)-i (2 * 5 = 32, 31 = 31 - 1 shift = * 2) 並且他是質數 
[为什么Java String哈希乘数为31？](https://juejin.im/post/5ba75d165188255c6a043b96). 更好的 hashCode 法請參考 com.google.common.hash.Hashing[Guava]

快速的寫法但是效能較差 boxing and unboxing
```java
// One-line hashCode method - mediocre performance
    @Override
    public int hashCode() {
        return Objects.hash(lineNum, prefix, areaCode);
    }
```

Immutable Object Lazy load 方法
```java
    // hashCode method with lazily initialized cached hash code
    private int hashCode; // Automatically initialized to 0
    
    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = Short.hashCode(areaCode);
            result = 31 * result + Short.hashCode(prefix);
            result = 31 * result + Short.hashCode(lineNum);
            hashCode = result;
        }
        return result;
    }
```
請撰寫 unit test 測一下約束 2, 如用 autovalue 自動生成可忽略測試. 可以忽略在 equals 中被比較的欄位, 但是需要注意必需忽略沒有在 equals 中被比較的欄位, 
不然可能會違反約束2.

## Item12 總是 override toString()

雖然 override toString 並不是一定要遵守的約束, 但是能讓使用者看的比較懂, 並且比較好 Debug.

## Item13 明智的 Override clone()

實際上一個 class implement Cloneable 



