package io.github.mrnuggelz.opensubsonic.responses

const val GetIndexesResponse = """
{
    "subsonic-response": {
        "status": "ok",
        "version": "1.16.1",
        "type": "AwesomeServerName",
        "serverVersion": "0.1.3 (tag)",
        "openSubsonic": true,
        "indexes": {
            "ignoredArticles": "The An A Die Das Ein Eine Les Le La",
            "index": [
                {
                    "name": "C",
                    "artist": [
                        {
                            "id": "100000016",
                            "name": "CARNÚN",
                            "coverArt": "ar-100000016",
                            "albumCount": 1
                        },
                        {
                            "id": "100000027",
                            "name": "Chi.Otic",
                            "coverArt": "ar-100000027",
                            "albumCount": 0
                        }
                    ]
                },
                {
                    "name": "I",
                    "artist": [
                        {
                            "id": "100000013",
                            "name": "IOK-1",
                            "coverArt": "ar-100000013",
                            "albumCount": 1
                        }
                    ]
                }
            ]
        }
    }
}
"""
