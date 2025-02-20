# Registration Page Test with Appium

## Prerequisites 🛠️
- **Appium Server** 2.0+ ([Installation Guide](https://appium.io/docs/en/2.0/quickstart/))
- **Node.js** 18.x or newer
- **Java JDK** 17+
- **Android SDK** (API 30+)
- **Appium Doctor** (for dependency verification):  
  `npm install -g appium-doctor`

## Technologies
- Java 17
- Maven 3.9.6
- Spring Boot 3.1.4
- **Appium Client** 8.6.0
- **Selenium** 4.8.0

## Installation
1. Clone the repository:  
   `git clone https://github.com/szlspetra/AppiumProject.git`
2. Install Appium dependencies:  
   `npm install -g appium`
3. Verify environment setup:  
   `appium-doctor --android` (for Android)  
4. Build the project:  
   `mvn clean install`

## Device Requirements 📱
### Android
- **Emulator**: Launch from Android Studio (recommended API 30+)
- **Physical Device**:
    - USB debugging enabled
    - Developer mode activated
    - Verify connection using `adb devices`

## Configuration Variables
| Variable Name       | Description                                     | Example Value                     |
|---------------------|-------------------------------------------------|-----------------------------------|
| `PLATFORM_VERSION`  | Platform version (e.g., Android API level)     | `15.0`                            |
| `PLATFORM_NAME`     | Platform name            | `Android`                         |
| `DEVICE_NAME`       | Device name/ID (verify with `adb devices`)     | `emulator-5554`                   |
| `APP_PACKAGE`       | Application package name                      | `com.interticket.budapest13`      |
| `APP_ACTIVITY`      | Main activity                                  | `com.interticket.smartcity.Ui.MainActivity` |
| `DRIVER_URL`        | Appium server URL                             | `http://127.0.0.1:4723`           |

## 🔒 Environment Variables Setup
All sensitive credentials (email, passwords, API keys) are stored in environment variables to prevent accidental exposure in code. These must be set before test execution, as the tests will fail if they're missing.

### Required Variables
| Variable Name | Description | Example Value |
|-----------------------|---------------------------------|-----------------------------|
| `GMAIL_ADDRESS` | Test account email | `testuser@gmail.com` |
| `GMAIL_APP_PWD` | App-specific password | `abcd efgh ijkl mnop` |
| `APP_PWD` | the password used for registration | `Abc123`

#### Setup Guide

Windows (PowerShell):
```
$env:GMAIL_ADDRESS = "your_email@gmail.com"
$env:GMAIL_APP_PWD = "your_gmail_app_password"
$env:APP_PWD = "your_bp13_password"
```

IntelliJ Configuration:
1. Run → Edit Configurations
2. Add variables under `Environment variables`:
```
GMAIL_ADDRESS=your_email@gmail.com;GMAIL_APP_PWD=your_app_password;APP_PWD=your_bp13_password
```

### Security Best Practices
1. 🔑 Create app-specific password:
   [Google App Passwords Guide](https://support.google.com/accounts/answer/185833)
2. 🚫 Never commit `.env` files
## Pre-run Checks ✅
1. Start Appium server:  
   `appium --allow-insecure=chromedriver_autodownload`
2. Launch emulator/device
3. Verify connection:  
