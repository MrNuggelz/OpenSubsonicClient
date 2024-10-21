package io.github.mrnuggelz.opensubsonic.responses

const val GetMusicFoldersResponse = """
{
    "subsonic-response": {
        "status": "ok",
        "version": "1.16.1",
        "type": "AwesomeServerName",
        "serverVersion": "0.1.3 (tag)",
        "openSubsonic": true,
        "musicFolders": {
            "musicFolder": [
                {
                    "id": "1",
                    "name": "music"
                },
                {
                    "id": "4",
                    "name": "upload"
                }
            ]
        }
    }
}
"""
