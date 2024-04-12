package sv.ufg.edu.fis.amb.administradortareasufg.util

import android.content.Context

object MySharedPreferences {
    private const val SHARED_PREF_NAME = "todos_mock"
    private const val KEY_JSON_DATA = "todos_json_data"

    fun saveJsonData(context: Context, jsonData: String) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_JSON_DATA, jsonData)
        editor.apply()
    }

    fun getJsonData(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_JSON_DATA, null)
    }
}