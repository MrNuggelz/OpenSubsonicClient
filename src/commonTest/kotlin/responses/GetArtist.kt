package io.github.mrnuggelz.opensubsonic.responses

const val GetArtistResponse = """
{
    "subsonic-response": {
        "status": "ok",
        "version": "1.16.1",
        "type": "AwesomeServerName",
        "serverVersion": "0.1.3 (tag)",
        "openSubsonic": true,
        "artist": {
            "id": "100000002",
            "name": "Synthetic",
            "coverArt": "ar-100000002",
            "albumCount": 1,
            "starred": "2021-02-22T05:54:18Z",
            "album": [
                {
                    "id": "200000002",
                    "parent": "100000002",
                    "album": "Colorsmoke EP",
                    "title": "Colorsmoke EP",
                    "name": "Colorsmoke EP",
                    "isDir": true,
                    "coverArt": "al-200000002",
                    "songCount": 12,
                    "created": "2021-02-23T04:24:48+00:00",
                    "duration": 4568,
                    "playCount": 1,
                    "artistId": "100000002",
                    "artist": "Synthetic",
                    "year": 2007,
                    "genre": "Electronic",
                    "userRating": 5,
                    "averageRating": 3,
                    "starred": "2021-02-22T05:51:53Z"
                }
            ]
        }
    }
}
"""
