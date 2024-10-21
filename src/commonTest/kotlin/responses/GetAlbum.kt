package io.github.mrnuggelz.opensubsonic.responses

const val GetAlbumResponse = """
{
    "subsonic-response": {
        "status": "ok",
        "version": "1.16.1",
        "type": "AwesomeServerName",
        "serverVersion": "0.1.3 (tag)",
        "openSubsonic": true,
        "album": {
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
            "genre": "Hip-Hop",
            "song": [
                {
                    "id": "300000116",
                    "parent": "200000021",
                    "title": "Can I Help U?",
                    "isDir": false,
                    "isVideo": false,
                    "type": "music",
                    "albumId": "200000021",
                    "album": "Forget and Remember",
                    "artistId": "100000036",
                    "artist": "Comfort Fit",
                    "coverArt": "300000116",
                    "duration": 103,
                    "bitRate": 216,
                    "bitDepth": 16,
                    "samplingRate": 44100,
                    "channelCount": 2,
                    "track": 1,
                    "year": 2005,
                    "genre": "Hip-Hop",
                    "size": 2811819,
                    "discNumber": 1,
                    "suffix": "mp3",
                    "contentType": "audio/mpeg",
                    "path": "user/Comfort Fit/Forget And Remember/1 - Can I Help U?.mp3"
                },
                {
                    "id": "300000121",
                    "parent": "200000021",
                    "title": "Planetary Picknick",
                    "isDir": false,
                    "isVideo": false,
                    "type": "music",
                    "albumId": "200000021",
                    "album": "Forget and Remember",
                    "artistId": "100000036",
                    "artist": "Comfort Fit",
                    "coverArt": "300000121",
                    "duration": 358,
                    "bitRate": 238,
                    "bitDepth": 16,
                    "samplingRate": 44100,
                    "channelCount": 2,
                    "track": 2,
                    "year": 2005,
                    "genre": "Hip-Hop",
                    "size": 10715592,
                    "discNumber": 1,
                    "suffix": "mp3",
                    "contentType": "audio/mpeg",
                    "path": "user/Comfort Fit/Forget And Remember/2 - Planetary Picknick.mp3"
                }
            ]
        }
    }
}
"""
