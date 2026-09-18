# 🛡️ Auto Totem

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Version](https://img.shields.io/badge/Version-1.5.0-red.svg)](https://github.com/NotY215/AutoTotem)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.9%20–%2026.3-green.svg)](https://minecraft.net)
[![Fabric](https://img.shields.io/badge/Fabric-0.19.5-yellow.svg)](https://fabricmc.net)
[![Java](https://img.shields.io/badge/Java-21%20%7C%2025-orange.svg)](https://openjdk.org)
[![Modrinth](https://img.shields.io/badge/Modrinth-NotY215-00AF5C.svg)](https://modrinth.com/user/NotY215)

**Automatic Off-Hand Totem Swapper for Fabric**

Auto Totem is a lightweight client-side utility mod that automatically moves a Totem of Undying into your off-hand when your health is critically low, and swaps it back when you recover.  
A simple keybind lets you enable or disable the feature on the fly.

**Supported versions:** Minecraft **1.21.9 → 26.3**

---

## ✨ Features

### 1. Health-Based Auto-Swap
When the feature is **enabled**:

| Condition | Action |
| --- | --- |
| Health ≤ **2 hearts** (4.0 HP) | Instantly finds the first Totem in your inventory and moves it to the off-hand |
| Health > **5 hearts** (10.0 HP) | Swaps the Totem back to the exact slot it came from |

### 2. Toggle Keybind
- Default key: **V**
- Pressing the key **enables / disables** the automatic swapping
- A clear status message appears on the action bar:
  - `AutoTotem: Enabled`
  - `AutoTotem: Disabled`

You can change the key in  
**Options → Controls → Key Binds → AutoTotem**

---

## 📦 Installation

1. Install **Fabric Loader** for your Minecraft version
2. Install the matching **Fabric API**
3. Download the **AutoTotem** jar built for your version
4. Place it in your `.minecraft/mods` folder
5. Launch the game with the Fabric profile

> This is a **client-side only** mod. It does not need to be installed on the server.

### Version note
Because Minecraft 26.1+ is unobfuscated (Mojang mappings) while 1.21.x used Yarn, **separate builds** are provided for:
- **1.21.9 – 1.21.11**
- **26.1.x**
- **26.2**
- **26.3**

Always download the jar that matches your game version.

---

## ⚙️ Configuration

Currently there is no config file.  
All behaviour is controlled by the keybind and the built-in health thresholds (2 hearts / 5 hearts).

---

## 📜 Technical Details

| Item | Value |
| --- | --- |
| Mod ID | `autototem` |
| Package | `com.noty.auto` |
| Minecraft | 1.21.9 – 26.3 |
| Fabric Loader | ≥ 0.16 (1.21.x) / ≥ 0.19.5 (26.x) |
| Fabric API | Required (version matching your Minecraft version) |
| Java | 21 (1.21.x) · 25 (26.x) |
| License | GPL-3.0 |

---

## 📄 License

This project is licensed under the **GNU General Public License v3.0**.  
See the [LICENSE](LICENSE) file for details.

---

## 🔗 Links

- **Modrinth**: [https://modrinth.com/user/NotY215](https://modrinth.com/user/NotY215)
- **Issues**: [https://github.com/NotY215/AutoTotem/issues](https://github.com/NotY215/AutoTotem/issues)
- **Source**: [https://github.com/NotY215/AutoTotem](https://github.com/NotY215/AutoTotem)