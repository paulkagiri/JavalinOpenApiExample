import io.javalin.http.Context
import io.javalin.openapi.*

object UserController {
    @OpenApi(
        path = "/api/v1/users",
        operationId = "register",
        methods = [HttpMethod.POST],
        summary = "Register a user",
        description = "Register a user",
        tags = ["Users"],
        requestBody = OpenApiRequestBody(
            content = [OpenApiContent(RegistrationRequest::class)],
            required = true,
            description = "Data about the account including the secret and it's permissions"
        ),
        responses = [
            OpenApiResponse(
                status = "200",
                description = "Returns dto representing configuration",
                content = [OpenApiContent(from = UserResponse::class)]
            ),
            OpenApiResponse(
                status = "401",
                description = "Returns 401 if token without moderation permission has been used to access this resource"
            ),
            OpenApiResponse(status = "404", description = "Returns 404 if non-existing configuration is requested")
        ]
    )
    fun register(context: Context) {

    }
}

data class RegistrationRequest(val name: String)

data class UserResponse(val id: String, val name: String)
