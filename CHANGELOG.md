## Release 1.1.0

### What's new for server owners
- **Paper 26.2 / Java 25** — built against the current Paper API. Spigot is no longer supported; use Paper or a Paper fork (Purpur etc.).
- **First-join messages** — a new `player-first-join-messages` list is used the very first time a player joins, so newcomers don't get a "Welcome back!". Leave the list empty to reuse the normal join messages.
- **PlaceholderAPI support** — any PAPI placeholder (`%player_displayname%`, `%vault_prefix%`, …) works in messages when PlaceholderAPI is installed. `%player%` still works without it.
- **Hex colors** — `&#rrggbb` works alongside the usual `&a` codes.
- **Silent join/leave permission** — players with `joinleavenotifier.silent` join and leave without a broadcast. Handy for staff and alts without needing a vanish plugin.
- **Vanish support actually works now** — v1.0.2 advertised SuperVanish support, but the check was never wired to the join/leave events, so vanished players were still announced. Fixed. Also covers PremiumVanish, EssentialsX and any plugin that sets the standard `vanished` metadata.
- **Toggle each side** — `join-enabled: false` / `leave-enabled: false` silence join or leave messages entirely.
- **Config upgrades itself** — new keys are added to your existing `config.yml` with their defaults; nothing you've customised is touched.
- **bStats** — anonymous usage metrics, opt out globally in `plugins/bStats/config.yml`.

### Changes (technical)
- Migrated from `spigot-api 1.21.1` to `paper-api 26.2.build.123-stable`; `api-version` is now `26.2`; Java 21 → 25
- Join/leave messages are now set via Paper's Adventure `PlayerJoinEvent#joinMessage(Component)` / `PlayerQuitEvent#quitMessage(Component)` instead of the deprecated `String` setters
- Removed the `MCDevTools`, `Lombok` and `SuperVanish` dependencies — replaced by Adventure's `LegacyComponentSerializer` (with `hexColors()`), a plain `registerEvents` call, and the metadata check respectively
- Collapsed `ConfigLoader`/`ConfigProvider` into `JoinLeaveNotifier#loadConfig()`, which uses Bukkit's `copyDefaults(true)` to backfill missing keys from the jar's `config.yml`; `MessageProcessor` reads config live so `/jln reload` is just `reloadConfig()`
- Merged `PlayerJoinListener` and `PlayerQuitListener` into `JoinLeaveListener`
- Added `bstats-bukkit 1.8` (shaded and relocated) — plugin id is `0` (metrics disabled) until one is registered
- Added `placeholderapi 2.12.3` as a `provided` dependency and `softdepend`
- Added a JUnit test for the no-repeat random message picker
- `maven-shade-plugin` bumped to 3.6.2 (3.6.0 cannot read Java 25 class files)
- Release workflow rewritten: manual `workflow_dispatch` (snapshot/release), release notes pulled from this file, publishes to GitHub Releases and Modrinth
- Bumped config version to `2`

## Release 1.0.2
### Changes
- Added SuperVanish / PremiumVanish soft-dependency (note: the check was not wired up — fixed in 1.1.0)
- Bumped MCDevTools dependency

## Release 1.0.1
### Changes
- Added GitHub Actions build and release workflows
- Updated default `config.yml` messages

## Release 1.0.0
- Initial release
