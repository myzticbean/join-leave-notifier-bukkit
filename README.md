# JoinLeaveNotifier - Spigot Plugin

JoinLeaveNotifier is a customizable Bukkit/Spigot plugin that displays unique and engaging messages when players join or leave your Minecraft server.

## Features

- Customizable join and leave messages
- Random message selection for variety
- Color code support for vibrant messages
- Easy configuration through `config.yml`
- Reload command for on-the-fly changes

## Installation

1. Download the `JoinLeaveNotifier.jar` file.
2. Place the jar file in your server's `plugins` folder.
3. Restart your server or use a plugin manager to load the plugin.
4. The plugin will generate a default `config.yml` file on first run.

## Configuration

Edit the `config.yml` file in the `plugins/JoinLeaveNotifier` folder to customize your messages:

```yaml
player-join-messages:
  - "&a&lWelcome back, &e%player%&a&l! &bWe missed you!"
  - "&6Look who decided to grace us with their presence! It's &e%player%&6!"
  # Add more join messages here...

player-leave-messages:
  - "&c%player% &4has left the building!"
  - "&eFarewell, &6%player%&e! We'll miss your shenanigans!"
  # Add more leave messages here...
config-version: 1
```
- Use `%player%` as a placeholder for the player's name.
- Use color codes with the `&` symbol (e.g., &a for green, &b for aqua, etc.).

## Commands

| Command                     | Description                      | Permission                |
|-----------------------------|----------------------------------|---------------------------|
| `/joinleavenotifier reload` | Reloads the plugin configuration | `joinleavenotifier.admin` |

## Permissions

| Permission                | Description                      | Default  |
|---------------------------|----------------------------------|----------|
| `joinleavenotifier.admin` | Allows use of the reload command | `op`     |

## Support

If you encounter any issues or have suggestions for improvements, please open an issue on our GitHub repository.

## License

This plugin is released under the MIT License. See the `LICENSE` file for more details.

## Contributing

We welcome contributions! Please feel free to submit a Pull Request.