# AnotherPunishGUI

AnotherPunishGUI is yet another punishment GUI! Because there is never enough punish GUI plugins! Easily punish cheaters,
spammers, and other targets on your server. Allow players to report their peers of misconduct, and easily view logs.

## Permissions
- `apgui.staff` - This permission gives staff the ability to run `/s` and all submodules contained within such as history, notes, and punish.
- `apgui.report` - Allows players to report their peers.
- `apgui.notify` - Players with this permission will be sent a chat message when reports are submitted.

## Commands
- `/s punish <player> (punishment)` - Allows a staff member to view a punishment GUI specific to the targeted player. 
Also allows non-player command senders to issue punishments if one is specified.
- `/s history <player>` - Creates a convenient GUI that shows a log of the player's last punishments and notes added by staff.
- `/s reports` - Shows a list of the most recent reports issued by players.
- `/s notes <player> <add/remove/clear> (note)` - Adds, removes, or clears notes for a given player.
- `/report <player> <reason>` - Lets players report their peers. This will recommend a list of reasons for the report but
allows players to pick their own reason.

## Default Config
```yaml
#     _                _   _               ____              _     _      ____   _   ___
#    / \   _ __   ___ | |_| |__   ___ _ __|  _ \ _   _ _ __ (_)___| |__  / ___| | | |_ _|
#   / _ \ | '_ \ / _ \| __| '_ \ / _ \ '__| |_) | | | | '_ \| / __| '_ \| |  _| | | || |
#  / ___ \| | | | (_) | |_| | | |  __/ |  |  __/| |_| | | | | \__ \ | | | |_| | |_| || |
# /_/   \_\_| |_|\___/ \__|_| |_|\___|_|  |_|    \__,_|_| |_|_|___/_| |_|\____|\___/|___|
# An arbitrary number of punishments may be defined.
# The internal name is used for tab-completing and the display-name for GUI.
# The type can either be "BAN" or "MUTE" depending on the type of punishment to deal.
# Durations are listed in hours.
punishments:
  xray:
    internal-name: "Xray"
    display-name: "&cXray Ban"
    material: "DIAMOND_ORE"
    type: "BAN"
    durations:
      - 24
      - 72
      - 168
      - 744
      - 8928
  flight:
    internal-name: "Flight"
    display-name: "&cFlight Ban"
    material: "FEATHER"
    type: "BAN"
    durations:
      - 744
      - 8928
  spamming:
    internal-name: "Spamming"
    display-name: "&3Spamming Mute"
    material: "GOAT_HORN"
    type: "MUTE"
    durations:
      - 1
      - 8
      - 24
      - 72
      - 168
  advertising:
    internal-name: "Advertising"
    display-name: "&eAdvertising Mute"
    material: "GOLD_INGOT"
    type: "MUTE"
    durations:
      - 24
      - 72
      - 168
      - 744
      - 8928

# These are reasons that a player may wish to report another player.
# Players will also be allowed to type in their own reason.
reasons:
  - "flight"
  - "xray"
  - "k-aura"
  - "reach"
  - "spamming"
  - "advertising"

# Whether to use the listed integration.
hooks:
  # LiteBans can be used to issue punishments as an alternative to the built-in system.
  lite-bans: true
```
