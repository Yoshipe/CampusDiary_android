# ==== Realmの使い方 ====

## RealmDBファイルへのパス
CampusDiary_android/app/src/main/java/com/watnow/campusdiary/RealmDB

## DBクラスの作り方

CalendarDB.kt

```
class CalendarDB: RealmObject() {
var strEvent: String = ""
var intDate: Int = 0
var strDetail: String = ""
}
```

## 登録の方法

HogeActivity.kt

```
class HogeActivity: AppCompatActivity() {
/* 中略 */

realm.beginTransaction()
calendarDB.strEvent = "予定1"
calendarDB.intDate = 1201
calendarDB.strDetail = "詳細"
realm.commitTransaction()
/*
この時点で、CalendarDBのrealmデータベースには、配列の0番目に
strEvent = "予定1"
intDate = 1201
strDetail = "詳細"
が保存された
*/

realm.beginTransaction()
calendarDB.strEvent = "予定2"
calendarDB.intDate = 1220
calendarDB.strDetail = "詳細2"
realm.commitTransaction()
/*
この時点で、CalendarDBのrealmデータベースには、配列の1番目に
strEvent = "予定2"
intDate = 1220
strDetail = "詳細2"
が保存された
*/
```

## 登録したデータの取得

```

// CalendarDBに保存されているデータを全て取ってくる
val results = realm.where(CalendarDB::class.java).findAll().sort(intDate)
// intDateの値にソートして取得
/*
resultsは配列となっていて、中身は
results[0].strEvent = "予定1"
results[1].strEvent = "予定2"
results.size == 2
となっている
*/

// 特定のデータを取ってくる(intDateが10のデータ)
val result = realm.where(CalendarDB::class.java).equalto("intDate", 10).findAll()

```