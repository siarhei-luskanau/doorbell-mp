import SwiftUI
import FirebaseCore
import FirebaseAuth
import ComposeApp

@main
struct ComposeApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView().ignoresSafeArea(.all)
        }
    }
}

struct ContentView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        FirebaseApp.configure()
        return MainKt.mainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        // Updates will be handled by Compose
    }
}
