import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

fun Project.configureSpotless() {
    val ktlintVersion = "0.41.0"
    val ktlintOptions = mapOf("indent_size" to "2", "continuation_indent_size" to "2")
    apply(plugin = Plugins.BuildPlugins.spotless)
    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("**/build/")
            ktlint(ktlintVersion).userData(ktlintOptions)
            ktfmt().googleStyle()
        }
        kotlinGradle {
            target("*.gradle.kts")
            ktlint(ktlintVersion).userData(ktlintOptions)
            ktfmt().googleStyle()
        }
        format("xml") {
            target("**/*.xml")
            targetExclude("**/build/", ".idea/")
            prettier(mapOf("prettier" to "2.0.5", "@prettier/plugin-xml" to "0.13.0"))
                .config(mapOf("parser" to "xml", "tabWidth" to 4))
        }
    }
}
