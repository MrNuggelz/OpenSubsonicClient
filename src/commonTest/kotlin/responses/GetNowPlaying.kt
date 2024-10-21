package io.github.mrnuggelz.opensubsonic.responses

const val GetNowPlayingResponse = """
{
    "subsonic-response": {
        "status": "ok",
        "version": "1.16.1",
        "type": "AwesomeServerName",
        "serverVersion": "0.1.3 (tag)",
        "openSubsonic": true,
        "nowPlaying": {
            "entry": [
                {
                    "id": "300115266",
                    "parent": "200147046",
                    "title": "Take the Home",
                    "isDir": false,
                    "isVideo": false,
                    "type": "music",
                    "albumId": "200147046",
                    "album": "How I Learned to Love Our Robot Overlords",
                    "artistId": "100002619",
                    "artist": "Raggedy Angry",
                    "coverArt": "300115266",
                    "duration": 227,
                    "bitRate": 222,
                    "bitDepth": 16,
                    "samplingRate": 44100,
                    "channelCount": 2,
                    "userRating": 3,
                    "track": 7,
                    "year": 2010,
                    "genre": "Industrial",
                    "size": 6341039,
                    "discNumber": 1,
                    "suffix": "mp3",
                    "contentType": "audio/mpeg",
                    "path": "Raggedy Angry/(2010) How I Learned to Love Our Robot Overlords/1-07 - Take the Home.mp3",
                    "username": "user",
                    "minutesAgo": 0,
                    "playerId": 0
                }
            ]
        }
    }
}
"""
