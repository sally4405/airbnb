import UIKit

class SearchTableFooterView: UIView {

    private var buttonConfiguration = { (title: String, isBold: Bool) -> UIButton.Configuration in
        var config = UIButton.Configuration.plain()
        var container = AttributeContainer()
        container.font = UIFont.systemFont(ofSize: 17, weight: isBold ? .bold : .regular)
        container.foregroundColor = UIColor.black
        config.attributedTitle = AttributedString(title, attributes: container)
        config.contentInsets = .zero
        return config
    }

    private lazy var eraseButton: UIButton = {
        var button = UIButton(configuration: buttonConfiguration("건너뛰기", false))
        return button
    }()

    private lazy var nextButton: UIButton = {
        var button = UIButton(configuration: buttonConfiguration("다음", true))
        button.isEnabled = false
        return button
    }()

    override init(frame: CGRect) {
        super.init(frame: frame)
        setUp()
    }

    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setUp()
    }

    private func setUp() {
        backgroundColor = .systemGray6
        setEraseButton()
        setNextButton()
    }

    private func setEraseButton() {
        addSubview(eraseButton)
        eraseButton.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            eraseButton.topAnchor.constraint(equalTo: self.topAnchor, constant: 14),
            eraseButton.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 16),
            eraseButton.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: -47)        ])
    }

    private func setNextButton() {
        addSubview(nextButton)
        nextButton.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            nextButton.topAnchor.constraint(equalTo: self.topAnchor, constant: 14),
            nextButton.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -16),
            nextButton.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: -47)
        ])
    }

    func toggleEraseButton() {
        eraseButton.setTitle(eraseButton.titleLabel?.text == "지우기" ? "건너뛰기" : "지우기", for: .normal)
    }

    func toggleNextButton() {
        nextButton.isEnabled = !nextButton.isEnabled
    }
}
