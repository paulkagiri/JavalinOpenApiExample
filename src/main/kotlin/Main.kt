import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.post
import io.javalin.openapi.plugin.OpenApiConfiguration
import io.javalin.openapi.plugin.OpenApiPlugin
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration
import io.javalin.openapi.plugin.swagger.SwaggerPlugin

fun main() {
    Javalin.create { config ->
        val openApiConfiguration = OpenApiConfiguration()
        openApiConfiguration.title = "Example Api"
        openApiConfiguration.description = "Example Api"
        openApiConfiguration.version = "1.0"
        config.registerPlugin(OpenApiPlugin(openApiConfiguration))

        val swaggerConfiguration = SwaggerConfiguration()
        swaggerConfiguration.uiPath = "/"
        config.registerPlugin(SwaggerPlugin(swaggerConfiguration))

        config.defaultContentType = "application/json"
        config.showJavalinBanner = false
        config.enableCorsForAllOrigins()
        config.enableDevLogging()
    }.routes {
        path("api/v1/users") {
            post(UserController::register)
        }
    }.start("0.0.0.0", 9000)
}

