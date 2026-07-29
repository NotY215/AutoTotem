# 🛡️ Auto Totem

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Version](https://img.shields.io/badge/Version-1.3-red.svg)](https://github.com/NotY215/AutoTotem)
[![Minecraft](https://img.shields.io/badge/Minecraft-26.1.2-green.svg)](https://minecraft.net)
[![Fabric](https://img.shields.io/badge/Fabric-0.19.3-yellow.svg)](https://fabricmc.net)
[![Java](https://img.shields.io/badge/Java-25-orange.svg)](https://openjdk.org)
[![Modrinth](https://img.shields.io/badge/Modrinth-NotY215-00AF5C.svg)](https://modrinth.com/user/NotY215)

**Automatic Off-Hand Totem Swapper for Fabric**

Auto Totem is a lightweight client-side utility mod that automatically moves a Totem of Undying into your off-hand when your health is critically low, and swaps it back when you recover.  
A simple keybind lets you enable or disable the feature on the fly.

---

## ✨ Features

### 1. Health-Based Auto-Swap
When the feature is **enabled**:

| Condition | Action |
|-----------|--------|
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

1. Install **Fabric Loader** for Minecraft **26.1.2**
2. Install **Fabric API**
3. Download the latest `AutoTotem.jar`
4. Place it in your `.minecraft/mods` folder
5. Launch the game with the Fabric profile

> This is a **client-side only** mod. It does not need to be installed on the server.

---

## ⚙️ Configuration

Currently there is no config file.  
All behaviour is controlled by the keybind and the built-in health thresholds (2 hearts / 5 hearts).

---

## 📜 Technical Details

| Item              | Value                  |
|-------------------|------------------------|
| Mod ID            | `autototem`            |
| Package           | `com.noty.auto`        |
| Minecraft         | 26.1.2                 |
| Fabric Loader     | ≥ 0.19.3               |
| Fabric API        | 0.155.2+26.1.2         |
| Java              | 25                     |
| License           | GPL-3.0                |

---

## 📄 License

This project is licensed under the **GNU General Public License v3.0**.  
See the [LICENSE](LICENSE) file for details.

---