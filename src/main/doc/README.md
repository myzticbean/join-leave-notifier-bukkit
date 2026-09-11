# JoinLeaveNotifier `v${project.version}`

JoinLeaveNotifier is a customizable Paper plugin that displays unique and engaging messages when players join or leave your Minecraft server.

Requires **Paper 26.2+** and **Java 25+**.

## Features

- Customizable join and leave messages
- Separate messages for a player's very first join
- Random message selection that avoids repeating recent messages
- Legacy `&` color codes and `&#rrggbb` hex colors
- [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) placeholders (optional)
- Vanished players (SuperVanish, PremiumVanish, EssentialsX, …) and players with `joinleavenotifier.silent` are never announced
- Easy configuration through `config.yml`
- Reload command for on-the-fly changes

## Installation

1. Download the `JoinLeaveNotifier-${project.version}.jar` file.
2. Place the jar file in your server's `plugins` folder.
3. Restart your server or use a plugin manager to load the plugin.
4. The plugin will generate a default `config.yml` file on first run.

## Configuration

Edit the `config.yml` file in the `plugins/JoinLeaveNotifier` folder to customize your messages:

```yaml
join-enabled: true
leave-enabled: true

player-join-messages:
  - "&a&lWelcome back, &e%player%&a&l! &bWe missed you!"
  - "&6Look who decided to grace us with their presence! It's &e%player%&6!"
  # Add more join messages here...

# Shown instead of player-join-messages the first time a player ever joins. Leave empty to reuse the join messages.
player-first-join-messages:
  - "&d&lWelcome to the server, &e%player%&d&l! &bMake yourself at home!"

player-leave-messages:
  - "&c%player% &4has left the building!"
  - "&eFarewell, &6%player%&e! We'll miss your shenanigans!"
  # Add more leave messages here...
config-version: 2
```
- Use `%player%` as a placeholder for the player's name. Any [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) placeholder (e.g. `%player_displayname%`, `%vault_prefix%`) works when PlaceholderAPI is installed.
- Use color codes with the `&` symbol (e.g., `&a` for green, `&b` for aqua) or hex colors like `&#ff8800`.
- Set `join-enabled` / `leave-enabled` to `false` to silence that side entirely.
- Configs from older versions are upgraded automatically; new keys are added with their defaults.

## Commands

| Command                     | Description                      | Permission                |
|-----------------------------|----------------------------------|---------------------------|
| `/joinleavenotifier reload` | Reloads the plugin configuration | `joinleavenotifier.admin` |

## Permissions

| Permission                | Description                      | Default  |
|---------------------------|----------------------------------|----------|
| `joinleavenotifier.admin` | Allows use of the reload command | `op`     |
| `joinleavenotifier.silent` | Join/leave without a broadcast (staff, alts) | `false`  |

## Support

If you encounter any issues or have suggestions for improvements, please create a post in our [discord](https://discord.gg/sK6qDtmRgF) under `#joinleavenotifier-help`.

## License

This plugin is released under the MIT License. See the `LICENSE` file for more details.

## Contributing

We welcome contributions! Please feel free to submit a Pull Request.