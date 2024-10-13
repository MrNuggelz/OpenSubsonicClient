package responses

const val GetChatMessages = """
{
  "subsonic-response": {
    "status": "ok",
    "version": "1.16.1",
    "type": "AwesomeServerName",
    "serverVersion": "0.1.3 (tag)",
    "openSubsonic": true,
    "chatMessages": {
      "chatMessage": [
        {
          "username": "admin",
          "time": 1678935707000,
          "message": "Api Script Testing"
        },
        {
          "username": "user",
          "time": 1678935699000,
          "message": "Api Script Testing"
        }
      ]
    }
  }
}
"""
