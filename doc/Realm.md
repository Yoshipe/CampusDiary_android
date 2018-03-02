# Realmの使い方

### Realmをプロジェクト内で一番に初期化する
git init 的な感じ
1. 任意のクラスを作成し、Applicationクラスを継承
2. onCreateメソッドをオーバーライドし、メソッド内で
```Application.kt
class MyApplication: Application() {
override fun onCreate() {
    super()
    Realm.init() // Realmの初期化作業
}
}
```

3. AndroidManifestに追記し、アプリが起動する一番最初にApplication.ktを実行するように設定する

以上の手順で、プロジェクト内でのRealmの初期化は完了


### DBに保存したい情報をメンバとして持ったクラスを作成、RealmObjectクラスを継承

``` SampleDB.kt
class SampleDB: RealmObject() {
/* メンバを記載 */
    var sampleString: String = ""
}
```

これで、Realmの初期化、RealmDBの作成が完了した


### コード内で用いる方法
RealmObjectを取得しなければならない（DBのクラスの型となる）

``` SampleActivity.kt
val realm = Realm.getDefaultInstance()
realm.beginTransaction()
/* データベース保存 */
relam.commitTransaction()
```

