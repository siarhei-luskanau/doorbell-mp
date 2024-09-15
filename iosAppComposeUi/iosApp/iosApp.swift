import UIKit
import FirebaseCore
import FirebaseAuth
import ComposeApp

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
    var window: UIWindow?

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
    ) -> Bool {
        FirebaseApp.configure()
        window = UIWindow(frame: UIScreen.main.bounds)
        if let window = window {
            window.rootViewController = MainKt.mainViewController(
                koin: KoinIosKt.doInitKoinIos(bundle: Bundle.main).koin
            )
            window.makeKeyAndVisible()
        }
        return true
    }
}
