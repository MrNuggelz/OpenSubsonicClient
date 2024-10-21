package io.github.mrnuggelz.opensubsonic.responses

const val GetMusicDirectoryResponse = """
{
    "subsonic-response": {
        "status": "ok",
        "version": "1.16.1",
        "type": "AwesomeServerName",
        "serverVersion": "0.1.3 (tag)",
        "openSubsonic": true,
        "directory": {
            "id": "1",
            "name": "music",
            "child": [
                {
                    "id": "100000016",
                    "parent": "1",
                    "isDir": true,
                    "title": "CARNÚN",
                    "artist": "CARNÚN",
                    "coverArt": "ar-100000016"
                },
                {
                    "id": "100000027",
                    "parent": "1",
                    "isDir": true,
                    "title": "Chi.Otic",
                    "artist": "Chi.Otic",
                    "coverArt": "ar-100000027"
                }
            ]
        }
    }
}
"""
