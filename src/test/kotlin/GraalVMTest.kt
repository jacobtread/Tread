import org.graalvm.polyglot.Context
import org.graalvm.polyglot.EnvironmentAccess
import org.graalvm.polyglot.HostAccess
import org.graalvm.polyglot.PolyglotAccess

fun main() {
    val context: Context = Context.newBuilder("js")
        .allowPolyglotAccess(PolyglotAccess.ALL)
        .allowHostAccess(HostAccess.ALL)
        .allowEnvironmentAccess(EnvironmentAccess.INHERIT)
        .allowExperimentalOptions(true)
        .build()
    context.getBindings("js").apply {
        putMember("test", Test())
    }
    context.eval("js", """
         const mf = function(event) {
            for (v in event) {
                print(v);
            }
         }
    """.trimIndent())


}

class Test {
    val name: String = "yassss"

    fun p() {
        println(name)
    }
}