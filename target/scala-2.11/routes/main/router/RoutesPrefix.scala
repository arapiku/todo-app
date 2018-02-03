
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/aratakenta/develop/lessons/todo-app/conf/routes
// @DATE:Fri Feb 02 23:12:29 JST 2018


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
