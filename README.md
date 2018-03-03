
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
 ##  RealmObjectを継承したクラスを作る
 
 ```sampleDB.kt
 class sampleDB: RealmObject() {
    var sampleData: String // データベースの一つの要素となる
}
```

## Realmのインスタンス取得と、終了について
onResumeメソッドでRealm.getDefaultInstance()でインスタンス取得
onPauseメソッドでrealm.close()で終了させる

 
 ```SampleActivity.kt
 /*中略 */
 override fun onResume() {
    super()
    realm = Realm.getDefaultInstance() // realmのインスタンス取得
}


 override fun onPause() {
    super()
    realm.close() // realmの終了
}
```

 
 ## 登録の方法
 
 ```sample.kt
 /* Realmのインスタンスをrealmとする */
 realm.beginTransaction()
 val DB = realm.createObject(SampleDB::class.java)
 DB.sampleData = "登録データ"
 realm.commitTransaction()
 ```
 
 これで、データベースの
 sampleDataに"登録データ"という文字列が保存できる

beginTransactionとcommitTransactioで挟んだ中でDB処理

