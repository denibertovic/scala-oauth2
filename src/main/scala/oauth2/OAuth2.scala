package oauth2

import dispatch._
import org.joda.time._

class OAuth2(appID: String, appSecret: String, redirectUri: String) {

  def getcode() : String = {
    //stage 1  - get code
    val url = :/("www.facebook.com", 443).secure / "dialog/oauth"
    val state = DateTime.now.getMillis.toString
    val params = Map("client_id" -> appID
                    ,"redirect_uri" -> redirectUri
                    ,"scope" -> ""
                    ,"state" -> state)
    val ret = (url <<? params to_uri).toString
    ret

  }
     
  def authorize(c: String): Map[String,String] = {
  // stage 2 - get access token
    val hX = new Http
    val url = :/("graph.facebook.com", 443).secure / "oauth/access_token"
    val params = Map("client_id" -> appID
                ,"redirect_uri" -> redirectUri
                ,"client_secret" -> appSecret
                ,"code" -> c)
    val ret = hX(url <<? params as_str).toString
    hX.shutdown()
    val res = ret.split('&').map { str =>
      val pair = str.split('=')
      (pair(0) -> pair(1))
    }.toMap
    res
  }
  
  def getUserInfo(res: Map[String,String]): String = {
    // stage 3 get user info
    val hX = new Http
    val stage3url = :/("graph.facebook.com", 443).secure / "me"    
    val stage3params = Map("access_token" -> res("access_token"))
   
    val info = hX(stage3url <<? stage3params as_str)
    hX.shutdown()
    info

  }



}
