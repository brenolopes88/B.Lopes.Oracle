package com.example.tarotoneinviewmode.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        LeituraEntity::class,
        CartaDaLeituraEntity::class,
        PraticaEntity::class,
        PraticaProgressoEntity::class,
        FavoritoEntity::class
    ],
    version = 7,
    exportSchema = true
)
abstract class OraculoDatabase : RoomDatabase() {
    abstract fun leituraDao(): LeituraDao
    abstract fun praticaDao(): PraticaDao
    abstract fun favoritoDao(): FavoritoDao

    companion object {
        @Volatile private var INSTANCE: OraculoDatabase? = null

        fun getInstance(context: Context): OraculoDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    OraculoDatabase::class.java,
                    "oraculo_db"
                )
                    .addMigrations(
                        MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, 
                        MIGRATION_4_5, MIGRATION_5_6, MIGRATION_6_7
                    )
                    .build().also { INSTANCE = it }
            }
        }

        val MIGRATION_6_7 = object : androidx.room.migration.Migration(6, 7) {
            override fun migrate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE leituras ADD COLUMN hexagramFutureNumber INTEGER DEFAULT NULL")
            }
        }

        val MIGRATION_1_2 = object : androidx.room.migration.Migration(1, 2) {
            override fun migrate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS praticas (
                        id TEXT PRIMARY KEY NOT NULL,
                        titulo TEXT NOT NULL,
                        descricao TEXT NOT NULL,
                        duracao TEXT NOT NULL,
                        categoria TEXT NOT NULL,
                        audioUrl TEXT NOT NULL,
                        ciclo INTEGER
                    )
                """)
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS pratica_progresso (
                        praticaId TEXT PRIMARY KEY NOT NULL,
                        diasCompletos INTEGER NOT NULL DEFAULT 0,
                        ultimaDataCompleta INTEGER
                    )
                """)
            }
        }
        val MIGRATION_2_3 = object : androidx.room.migration.Migration(2, 3) {
            override fun migrate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE leituras ADD COLUMN hexagramNumber INTEGER DEFAULT NULL")
            }
        }
        val MIGRATION_3_4 = object : androidx.room.migration.Migration(3, 4) {
            override fun migrate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE praticas ADD COLUMN isPremium INTEGER NOT NULL DEFAULT 0")
            }
        }
        val MIGRATION_4_5 = object : androidx.room.migration.Migration(4, 5) {
            override fun migrate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE leituras ADD COLUMN isFavorite INTEGER NOT NULL DEFAULT 0")
            }
        }
        val MIGRATION_5_6 = object : androidx.room.migration.Migration(5, 6) {
            override fun migrate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS favoritos (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        tipo TEXT NOT NULL,
                        refId TEXT NOT NULL,
                        dataCriacao INTEGER NOT NULL
                    )
                """)
            }
        }
    }
}
