package com.kodev.games.utils

import com.kodev.games.data.source.local.entity.GameEntity

object DataDummy {

    fun generateDataGames(): List<GameEntity> {

        val games = ArrayList<GameEntity>()
        games.add(
            GameEntity(
                3498,
                "Grand Theft Auto V",
                "<p>Rockstar Games went bigger, since their previous installment of the series. You get the complicated and realistic world-building from Liberty City of GTA4 in the setting of lively and diverse Los Santos, from an old fan favorite GTA San Andreas. 561 different vehicles (including every transport you can operate) and the amount is rising with every update. <br />\nSimultaneous storytelling from three unique perspectives: <br />\nFollow Michael, ex-criminal living his life of leisure away from the past, Franklin, a kid that seeks the better future, and Trevor, the exact past Michael is trying to run away from. <br />\nGTA Online will provide a lot of additional challenge even for the experienced players, coming fresh from the story mode. Now you will have other players around that can help you just as likely as ruin your mission. Every GTA mechanic up to date can be experienced by players through the unique customizable character, and community content paired with the leveling system tends to keep everyone busy and engaged.</p>",
                "17-09-2013",
                "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg",
                "4.48",
                "PC, Xbox Series S/X, PlayStation 3, PlayStation 4, PlayStation 5, Xbox 360, Xbox One",
                "Action, Adventure",
                "Windows 10 64 Bit, Windows 8.1 64 Bit, Windows 8 64 Bit, Windows 7 64 Bit Service Pack 1, Windows Vista 64 Bit Service Pack 2* (*NVIDIA video card recommended if running Vista OS)Processor: Intel Core 2 Quad CPU Q6600 @ 2.40GHz (4 CPUs) / AMD Phenom 9850 Quad-Core Processor (4 CPUs) @ 2.5GHzMemory: 4 GB RAMGraphics: NVIDIA 9800 GT 1GB / AMD HD 4870 1GB (DX 10, 10.1, 11)Storage: 72 GB available spaceSound Card: 100% DirectX 10 compatibleAdditional Notes: Over time downloadable content and programming changes will change the system requirements for this game.  Please refer to your hardware manufacturer and www.rockstargames.com/support for current compatibility information. Some system components such as mobile chipsets, integrated, and AGP graphics cards may be incompatible. Unlisted specifications may not be supported by publisher.     Other requirements:  Installation and online play requires log-in to Rockstar Games Social Club (13+) network; internet connection required for activation, online play, and periodic entitlement verification; software installations required including Rockstar Games Social Club platform, DirectX , Chromium, and Microsoft Visual C++ 2008 sp1 Redistributable Package, and authentication software that recognizes certain hardware attributes for entitlement, digital rights management, system, and other support purposes.     SINGLE USE SERIAL CODE REGISTRATION VIA INTERNET REQUIRED; REGISTRATION IS LIMITED TO ONE ROCKSTAR GAMES SOCIAL CLUB ACCOUNT (13+) PER SERIAL CODE; ONLY ONE PC LOG-IN ALLOWED PER SOCIAL CLUB ACCOUNT AT ANY TIME; SERIAL CODE(S) ARE NON-TRANSFERABLE ONCE USED; SOCIAL CLUB ACCOUNTS ARE NON-TRANSFERABLE.  Partner Requirements:  Please check the terms of service of this site before purchasing this software.",
            )
        )

        games.add(
            GameEntity(
                3498,
                "The Witcher 3: Wild Hunt",
                "<p>The third game in a series, it holds nothing back from the player. Open world adventures of the renowned monster slayer Geralt of Rivia are now even on a larger scale. Following the source material more accurately, this time Geralt is trying to find the child of the prophecy, Ciri while making a quick coin from various contracts on the side. Great attention to the world building above all creates an immersive story, where your decisions will shape the world around you.</p>\n<p>CD Project Red are infamous for the amount of work they put into their games, and it shows, because aside from classic third-person action RPG base game they provided 2 massive DLCs with unique questlines and 16 smaller DLCs, containing extra quests and items.</p>\n<p>Players praise the game for its atmosphere and a wide open world that finds the balance between fantasy elements and realistic and believable mechanics, and the game deserved numerous awards for every aspect of the game, from music to direction.</p>",
                "18-05-2015",
                "https://media.rawg.io/media/games/618/618c2031a07bbff6b4f611f10b6bcdbc.jpg",
                "4.67",
                "Nintendo Switch, PlayStation 5, Xbox Series S/X, Xbox One, PlayStation 4",
                "Action, Adventure, RPG",
                "Intel CPU Core i5-2500K 3.3GHz\n" +
                        "AMD CPU Phenom II X4 940\n" +
                        "Nvidia GPU GeForce GTX 660\n" +
                        "AMD GPU Radeon HD 7870\n" +
                        "RAM 6GB\n" +
                        "OS 64-bit Windows 7 or 64-bit Windows 8 (8.1)\n" +
                        "DirectX 11\n" +
                        "HDD Space 40 GB",
            )
        )

        games.add(
            GameEntity(
                3498,
                "The Elder Scrolls V: Skyrim",
                "<p>The fifth game in the series, Skyrim takes us on a journey through the coldest region of Cyrodiil. Once again player can traverse the open world RPG armed with various medieval weapons and magic, to become a hero of Nordic legends –Dovahkiin, the Dragonborn. After mandatory character creation players will have to escape not only imprisonment but a fire-breathing dragon. Something Skyrim hasn’t seen in centuries.<br />\nAfter Oblivion, the magic system was reworked, in order to show players more aggressive and direct combat. As a Dragonborn, your character will be able to use the powerful magic of dragon shouts, powered, upgraded and researched with the souls of the dragons that will be randomly encountered by players, while traveling. Hundreds of sidequests will invite players to discover every part of the newly introduced land. Aside from already filled with guilds, Daedra and steampunk Dwemer ruins, Skyrim has additional DLC content that not only brings Solstheim island and vampire conclave but gives players the ability to build their own homes, instead of buying pre-made ones.</p>",
                "11-11-2011",
                "https://media.rawg.io/media/games/7cf/7cfc9220b401b7a300e409e539c9afd5.jpg",
                "4.42",
                "PC, Nintendo Switch, Xbox 360, PlayStation 3",
                "Action, RPG",
                "<strong>Minimum:</strong><br>\t<ul class=\\\"bb_ul\\\"><li><strong>OS:</strong> Windows 7/Vista/XP PC (32 or 64 bit)<br>\t</li><li><strong>Processor:</strong> Dual Core 2.0GHz or equivalent processor<br>\t</li><li><strong>Memory:</strong> 2GB System RAM<br>\t</li><li><strong>Hard Disk Space:</strong> 6GB free HDD Space<br>\t</li><li><strong>Video Card:</strong> Direct X 9.0c compliant video card with 512 MB of RAM<br>\t</li><li><strong>Sound:</strong> DirectX compatible sound card<br>\t</li></ul>",
            )
        )

        return games
    }
}
