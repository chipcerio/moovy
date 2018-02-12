package com.chipcerio.moovy.data.source.json

object Trending {

    val trendingMovies = """
        [
	        {
                "watchers": 151,
                "movie": {
                    "title": "War for the Planet of the Apes",
                    "year": 2017,
                    "ids": {
                        "trakt": 178749,
                        "slug": "war-for-the-planet-of-the-apes-2017",
                        "imdb": "tt3450958",
                        "tmdb": 281338
                    }
                }
            },
            {
                "watchers": 99,
                "movie": {
                    "title": "Spider-Man: Homecoming",
                    "year": 2017,
                    "ids": {
                        "trakt": 196213,
                        "slug": "spider-man-homecoming-2017",
                        "imdb": "tt2250912",
                        "tmdb": 315635
                    }
                }
            }
        ]
        """

    val movieId_281338 = """
        {
            "id": 281338,
            "backdrops": [
                {
                    "aspect_ratio": 1.777777777777778,
                    "file_path": "/ulMscezy9YX0bhknvJbZoUgQxO5.jpg",
                    "height": 2160,
                    "iso_639_1": null,
                    "vote_average": 5.454,
                    "vote_count": 3,
                    "width": 3840
                },
                {
                    "aspect_ratio": 1.777777777777778,
                    "file_path": "/w4ZYFLJbiqZyvCcGHgvaHD6lTqQ.jpg",
                    "height": 1080,
                    "iso_639_1": null,
                    "vote_average": 5.318,
                    "vote_count": 3,
                    "width": 1920
                }
            ],
            "posters": [
                {
                    "aspect_ratio": 0.6666666666666666,
                    "file_path": "/4Ar01t6sW1ZZBcbz2R1wqjzIBdr.jpg",
                    "height": 3000,
                    "iso_639_1": "fr",
                    "vote_average": 5.454,
                    "vote_count": 3,
                    "width": 2000
                },
                {
                    "aspect_ratio": 0.6666666666666666,
                    "file_path": "/3vYhLLxrTtZLysXtIWktmd57Snv.jpg",
                    "height": 1500,
                    "iso_639_1": "en",
                    "vote_average": 5.394,
                    "vote_count": 10,
                    "width": 1000
                }
            ]
        }
        """



}