package io.github.mrnuggelz.opensubsonic.responses

const val GetAlbumListResponse = """
{
    "subsonic-response": {
        "status": "ok",
        "version": "1.16.1",
        "type": "AwesomeServerName",
        "serverVersion": "0.1.3 (tag)",
        "openSubsonic": true,
        "albumList": {
            "album": [
                {
                    "id": "200000021",
                    "parent": "100000036",
                    "album": "Forget and Remember",
                    "title": "Forget and Remember",
                    "name": "Forget and Remember",
                    "isDir": true,
                    "coverArt": "al-200000021",
                    "songCount": 20,
                    "created": "2021-07-22T02:09:31+00:00",
                    "duration": 4248,
                    "playCount": 0,
                    "artistId": "100000036",
                    "artist": "Comfort Fit",
                    "year": 2005,
                    "genre": "Hip-Hop"
                },
                {
                    "id": "200000012",
                    "parent": "100000019",
                    "album": "Buried in Nausea",
                    "title": "Buried in Nausea",
                    "name": "Buried in Nausea",
                    "isDir": true,
                    "coverArt": "al-200000012",
                    "songCount": 9,
                    "created": "2021-02-24T01:44:21+00:00",
                    "duration": 1879,
                    "playCount": 0,
                    "artistId": "100000019",
                    "artist": "Various Artists",
                    "year": 2012,
                    "genre": "Punk"
                }
            ]
        }
    }
}
"""
