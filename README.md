# notifyme telegram bot

## Prerequisites

* Java 8
* Maven
* Telegram account

## Usage

Generate a new bot with `t.me/botfather`.
Update app configuration in application.properties file:

```bash
bot.name=<bot name>
bot.token=<bot token>
bot.allowedUsers=<comma separated list of telegram usernames>
```

Build application `mvn clean install`
and run bot with following command
`java -jar notifyme-0.0.1-SNAPSHOT-jar-with-dependencies.jar`


After that follow url `t.me/<bot name>` and execute command `/join` (sent message to the bot).
As result of this command execution username and chatId will be saved (mapdb file stored in user home
directory with name `.notifyme.data`).

After this operation you can execute POST request on 0.0.0.0:4567/notify/<telegram username> + notification text in the body of request. 

Execute /leave command to remove your username/chatId from the bot storage.

## TODO
* Add ability to send messages with attachments
* Add simple auth
* Make port configurable
* Add dockerfile
* Add ability to read bot name/token from env variables
* Add tests
* Add logging

## Links
1. [MapDB](https://jankotek.gitbooks.io/mapdb/content/)
2. [TelegramBots](https://github.com/rubenlagus/TelegramBots/wiki/Getting-Started)
3. [SparkJava](https://sparkjava.com/)

## License
[MIT](https://choosealicense.com/licenses/mit/)