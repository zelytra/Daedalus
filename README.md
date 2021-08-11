# Deadalus

![](https://github.com/zelytra/Daedalus/blob/master/img/logo.png)

![release](https://img.shields.io/badge/release-v0.8-blueviolet) ![language](https://img.shields.io/badge/langage-JAVA-orange) ![license](https://img.shields.io/badge/license-GPL--3.0-blue)


## Presentation

Daedalus is a project taking you back to Ancient Greece. Four human teams wander the Labyrinth of Daedalus, austere dwelling of the Minotaur and its minions. A hostile, brutal and irrevocable encounter is to expect, unless our mortal friends win the favour of the Gods of Olympus. Only one team shall emerge victorious, but which one will it be ?


## Setup

### Server:

First, you need to setup a [**paper**](https://papermc.io/downloads) server in **1.16.4/1.16.5**. I advise you to allocate 4G ram:  
`java -Xms1G -Xmx4G -jar server.jar nogui`

After settings up the server, download the latest [**release**](https://github.com/zelytra/Daedalus/releases) of the plugin and put it inside _plugins_ folder of your server.

**IMPORTANT** : you also need to install [**FAWE**](https://ci.athion.net/job/FastAsyncWorldEdit-1.16/) \(FastAsyncWorldEdit\). The plugin maze generation depend on it to generate the Labyrinth. \(The bukkit version\)

**NB** : If you try to setup the server on your local machine, please check if you correctly have Java _JDK 16_ install on it unless you will not be able to launch the server.

**WARNING** : The plugin is not supported on spigot server. It can create some issues on labyrinth generation and shrink manager.

### On Start

Before starting the server, I advise you to change these two lines inside _paper.yml_ file \(to avoid warning spam in console while maze generation\) :

```text
watchdog:  
  early-warning-every: -1
  early-warning-delay: -1
```

At the first boot, the plugin will download the map of the mini-game \(you cannot use custom map\).

> The map size is about _85MB_ so prepare the server before inviting your friends !

```text
[Daedalus]>> Downloading map please wait...  
[Daedalus]>> Extracting files please wait...
```

At this point,the server will make some modifications and will need a restart. After this restart the map is setup and ready to be played !

### Reset

To reset the map, you only need to reboot the server :

> /stop or /restart as you want

It will reset the map \(the plugin is smart, it will not download the map again, only extracting files !\)

## Documentation

* [**WIKI**](http://wiki.mc-daedalus.com) 
* [**WEB Site**](https://mc-daedalus.com) 

## Credits

* **Spirit\_\_10** : Project Founder & Builder
* **Ichabodt** : Artistic Director & Builder
* **Minucia** : Graphic designer
* [**Zelytra**](https://github.com/zelytra) : Developer

## Thanks

* [**Nicolas61x**](https://github.com/Nicolas62x) : Help on maze optimization
* [**punisher5**](https://github.com/JohnPoliakov) : Help on GameManager part

