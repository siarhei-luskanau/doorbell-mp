import com.google.gson.Gson
import java.io.File

class GoogleServicesJsonParser(googleServicesJsonFile: File, private val packageName: String) {

    private val googleServicesJsonData: GoogleServicesJsonData by lazy {
        val jsonText = googleServicesJsonFile.readText()
        Gson().fromJson(jsonText, GoogleServicesJsonData::class.java)
    }

    fun googleApiKey(): String? = getClient()?.apiKey?.firstOrNull()?.currentKey

    fun googleAppId(): String? = getClient()?.clientInfo?.mobilesdkAppId

    fun firebaseDatabaseUrl(): String? = googleServicesJsonData.projectInfo?.firebaseUrl

    fun gcmDefaultSenderId(): String? = googleServicesJsonData.projectInfo?.projectNumber

    fun googleStorageBucket(): String? = googleServicesJsonData.projectInfo?.storageBucket

    fun projectId(): String? = googleServicesJsonData.projectInfo?.projectId

    private fun getClient(): Client? = googleServicesJsonData.client?.firstOrNull {
        it.clientInfo?.androidClientInfo?.packageName == packageName
    }
}
