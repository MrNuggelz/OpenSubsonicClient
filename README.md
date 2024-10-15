# OpenSubsonic client

A client implementation for the OpenSubsonic API. Currently, there is no support for videos.

## Implemented API endpoints

### System

| Endpoint                  | Status             |
|---------------------------|--------------------|
| ping                      | :heavy_check_mark: |
| getLicense                | :heavy_check_mark: |
| getOpenSubsonicExtensions | TODO               |

### Browsing

| Endpoint          | Status             | Note        |
|-------------------|--------------------|-------------|
| getIndexes        | Not Planned        | Maybe later |
| getMusicFolders   | Not Planned        | Maybe later |
| getMusicDirectory | Not Planned        | Maybe later |
| getGenres         | :heavy_check_mark: |
| getArtists        | :heavy_check_mark: |
| getArtist         | :heavy_check_mark: |
| getAlbum          | :heavy_check_mark: |
| getSong           | :heavy_check_mark: |
| getVideos         | Not Planned        | Maybe later |
| getVideoInfo      | Not Planned        | Maybe later |
| getArtistInfo     | :heavy_check_mark: |
| getArtistInfo2    | :heavy_check_mark: |
| getAlbumInfo      | :heavy_check_mark: |
| getAlbumInfo2     | :heavy_check_mark: |
| getSimilarSongs   | :heavy_check_mark: |
| getSimilarSongs2  | :heavy_check_mark: |
| getTopSongs       | :heavy_check_mark: |

### Album/song lists

| Endpoint        | Status             | Note              |
|-----------------|--------------------|-------------------|
| getAlbumList    | :heavy_check_mark: | Use getAlbumList2 |
| getAlbumList2   | :heavy_check_mark: |
| getRandomSongs  | :heavy_check_mark: |
| getSongsByGenre | :heavy_check_mark: |
| getNowPlaying   | :heavy_check_mark: |
| getStarred      | :heavy_check_mark: |
| getStarred2     | :heavy_check_mark: |

### Searching

| Endpoint | Status             | Note        |
|----------|--------------------|-------------|
| search   | Not Planned        | Use search3 |
| search2  | Not Planned        | Use search3 |
| search3  | :heavy_check_mark: |

### Playlists

| Endpoint       | Status             |
|----------------|--------------------|
| getPlaylists   | :heavy_check_mark: |
| getPlaylist    | :heavy_check_mark: |
| createPlaylist | :heavy_check_mark: |
| updatePlaylist | :heavy_check_mark: |
| deletePlaylist | :heavy_check_mark: |

### Media retrieval

| Endpoint    | Status             | Note        |
|-------------|--------------------|-------------|
| stream      | :heavy_check_mark: |
| download    | :heavy_check_mark: |
| getCoverArt | :heavy_check_mark: |
| hls         | Not Planned        | Maybe later |
| getCaptions | Not Planned        | Maybe later |
| getLyrics   | :heavy_check_mark: |
| getAvatar   | :heavy_check_mark: |

### Media annotation

| Endpoint  | Status             |
|-----------|--------------------|
| star      | :heavy_check_mark: |
| unstar    | :heavy_check_mark: |
| setRating | :heavy_check_mark: |
| scrobble  | :heavy_check_mark: |

### Sharing

| Endpoint    | Status             |
|-------------|--------------------|
| getShares   | :heavy_check_mark: |
| createShare | :heavy_check_mark: |
| updateShare | :heavy_check_mark: |
| deleteShare | :heavy_check_mark: |

### Podcast

| Endpoint               | Status |
|------------------------|--------|
| getPodcasts            | TODO   |
| getNewestPodcasts      | TODO   |
| refreshPodcasts        | TODO   |
| createPodcastChannel   | TODO   |
| deletePodcastChannel   | TODO   |
| deletePodcastEpisode   | TODO   |
| downloadPodcastEpisode | TODO   |

### Jukebox

| Endpoint       | Status |
|----------------|--------|
| jukeboxControl | TODO   |

### Internet radio

| Endpoint                   | Status             |
|----------------------------|--------------------|
| getInternetRadioStations   | :heavy_check_mark: |
| createInternetRadioStation | :heavy_check_mark: |
| updateInternetRadioStation | :heavy_check_mark: |
| deleteInternetRadioStation | :heavy_check_mark: |

### Chat

| Endpoint        | Status             |
|-----------------|--------------------|
| getChatMessages | :heavy_check_mark: |
| addChatMessage  | :heavy_check_mark: |

### User management

| Endpoint       | Status |
|----------------|--------|
| getUser        | TODO   |
| getUsers       | TODO   |
| createUser     | TODO   |
| updateUser     | TODO   |
| deleteUser     | TODO   |
| changePassword | TODO   |

### Bookmarks

| Endpoint       | Status             |
|----------------|--------------------|
| getBookmarks   | :heavy_check_mark: |
| createBookmark | :heavy_check_mark: |
| deleteBookmark | :heavy_check_mark: |
| getPlayQueue   | TODO               |
| savePlayQueue  | :heavy_check_mark: |

### Media library scanning

| Endpoint      | Status             |
|---------------|--------------------|
| getScanStatus | :heavy_check_mark: |
| startScan     | :heavy_check_mark: |
