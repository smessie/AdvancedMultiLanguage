# AdvancedMultiLanguage
Author: [smessie](https://twitter.com/smessie_) 
SpigotMC page: https://www.spigotmc.org/resources/advanced-multi-language.21338/

#### How to implement new language for developers
1. Register language command as alias in plugin.yml
2. Register language command as alias in bungeecord/commands/English.java constructor
3. Add language to HashMaps in main/Languages.java
4. Add language abbreviation to config.yml (under `languages` and `warn-on-select`)
5. Create new message file with the translation under /translations
6. Add language as enum to api/Language.java
7. Add to SpigotMC resource page


#### How to request a new language for server owners
Please, send me a PM to suggest new languages.
What must be in your PM:
1. Translation of "Usage: /language <language>"
2. Translation of "This language is disabled."
3. Name of your language in your own language.
4. Translation of the name in English.
5. Translation of "Your language is set to English"
6. Translation of "Only ingame players can set their language."
7. The abbreviation of your language
8. Translation of ""Language English not found."
9. Translation of "Attention! You may not speak English in the chat."