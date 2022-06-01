import UIKit

@main
class AppDelegate: UIResponder, UIApplicationDelegate {

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        
        let navBarAppearance = customNavBarAppearance()
        
        let appearance = UINavigationBar.appearance()
        appearance.scrollEdgeAppearance = navBarAppearance
        appearance.compactAppearance = navBarAppearance
        appearance.standardAppearance = navBarAppearance
        
        return true
    }

    func application(_ application: UIApplication, configurationForConnecting connectingSceneSession: UISceneSession, options: UIScene.ConnectionOptions) -> UISceneConfiguration {
        return UISceneConfiguration(name: "Default Configuration", sessionRole: connectingSceneSession.role)
    }

    func customNavBarAppearance() -> UINavigationBarAppearance {
        let customNavBarAppearance = UINavigationBarAppearance()
        customNavBarAppearance.backgroundColor = .systemGray6
        return customNavBarAppearance
    }

}

