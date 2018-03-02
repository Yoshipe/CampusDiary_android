
# ==== Emojis ====
 🐛 バグ修正  
 👍  機能改善  
 ✨  部分的な機能追加  
 ⚡️  パフォーマンス改善  
 💄  UIやスタイルの更新  
 ♻️   リファクタリング  
 🚿  不要な機能・使われなくなった機能の削除  
 💚  テストやCIの修正・改善  
 👕  Lintエラーの修正やコードスタイルの修正  
 🆙  依存パッケージなどのアップデート  
 📝  ドキュメント更新  
 
 # ==== Realmの使い方 ====
 DBとなるクラスを作る(メンバーにDBに保存したい情報を持つ)
 
 ```sampleDB.kt
 class sampleDB: RealmObject() {
    var sampleData: String // データベースの一つのプロパティとなる
}
```
 
 ActivityクラスのonResumeメソッドでRealm.getDefaultInstance()でインスタンス取得
 
 ```SampleActivity.kt
 /*中略 */
 override fun onResume() {
    super()
    realm = Realm.getDefaultInstance() // realmのインスタンス取得
}
```
 
 同クラス内のonPauseメソッドでrealm.close()で終了させる
 
```SampleActivity.kt
 /* 中略 */
 override fun onPause() {
    super()
    realm.close() // realmの終了
}
```

 
 ## 登録の方法
 
 ```sample.kt
 /* Realmのインスタンスをrealmとする */
 realm.beginTransaction()
 val DB = realm.createObject(/* DBクラス::class.java */ )
 DB./* プロパティ名 */  = 保存したいデータ
 realm.commitTransaction()
 ```

realmオブジェクトのプロパティにアクセスして代入する感じ

修正の場合は、

```sample.kt
val results = realm.where(/* DBクラス::class.java */).findAll().sort(/* キー名 */)
みたいにして、Realmオブジェクトを取ってくる。これは配列だから
results[index]
```

としてアクセスできる
