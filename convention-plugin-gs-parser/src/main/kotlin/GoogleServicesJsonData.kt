import com.google.gson.annotations.SerializedName

internal data class GoogleServicesJsonData(
    @SerializedName("project_info") val projectInfo: ProjectInfo? = null,
    @SerializedName("client") val client: List<Client>? = null
)

data class ProjectInfo(
    @SerializedName("project_number") val projectNumber: String? = null,
    @SerializedName("firebase_url") val firebaseUrl: String? = null,
    @SerializedName("project_id") val projectId: String? = null,
    @SerializedName("storage_bucket") val storageBucket: String? = null
)

data class Client(
    @SerializedName("client_info") val clientInfo: ClientInfo? = null,
    @SerializedName("api_key") val apiKey: List<CurrentKey>? = null
)
data class ClientInfo(
    @SerializedName("mobilesdk_app_id") val mobilesdkAppId: String? = null,
    @SerializedName("android_client_info") val androidClientInfo: AndroidClientInfo? = null
)

data class AndroidClientInfo(@SerializedName("package_name") val packageName: String? = null)

data class CurrentKey(@SerializedName("current_key") val currentKey: String? = null)
