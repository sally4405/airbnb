import UIKit

class SearchTableFooterView: UIView {

//    private lazy var eraseButton: UIButton = {
//        var button = UIButton()
//        button.titleLabel?.text = "지우기"
//        button.titleLabel?.font = .systemFont(ofSize: 17)
//        return button
//    }()
//
//    private lazy var nextButton: UIButton = {
//        var button = UIButton()
//        button.titleLabel?.text = "다음"
//        button.titleLabel?.font = .systemFont(ofSize: 17, weight: .bold)
//        button.isEnabled = false
//        return button
//    }()

    private lazy var eraseButton: UILabel = {
        var button = UILabel()
        button.text = "지우기"
        button.font = .systemFont(ofSize: 17)
        return button
    }()

    private lazy var nextButton: UILabel = {
        var button = UILabel()
        button.text = "다음"
        button.font = .systemFont(ofSize: 17, weight: .bold)
        button.isEnabled = false
        return button
    }()

    override init(frame: CGRect) {
        super.init(frame: frame)
        setUI()
        setConstraint()
    }

    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setUI()
        setConstraint()
    }

    private func setUI() {
        backgroundColor = .systemGray6
        addSubview(eraseButton)
        addSubview(nextButton)
    }

    private func setConstraint() {
        eraseButton.translatesAutoresizingMaskIntoConstraints = false
        nextButton.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            eraseButton.topAnchor.constraint(equalTo: self.topAnchor, constant: 14),
            eraseButton.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 16),
            eraseButton.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: -47),

            nextButton.topAnchor.constraint(equalTo: self.topAnchor, constant: 14),
            nextButton.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -16),
            nextButton.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: -47),
        ])
    }

    func toggleEraseButton() {
//        eraseButton.titleLabel?.text = eraseButton.titleLabel?.text == "지우기" ? "건너뛰기" : "지우기"
    }

    func toggleNextButton() {
        nextButton.isEnabled = !nextButton.isEnabled
    }
}
