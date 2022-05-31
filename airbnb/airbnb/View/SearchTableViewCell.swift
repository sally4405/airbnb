import UIKit

class SearchTableViewCell: UITableViewCell {

    static let identifier: String = "SearchTableViewCell"

    private var titleLabel: UILabel = {
        var label = UILabel()
        label.font = .systemFont(ofSize: 17)
        return label
    }()

    private var contentLabel: UILabel = {
        var label = UILabel()
        label.font = .systemFont(ofSize: 17)
        label.textColor = .systemGray3
        return label
    }()

    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        setUI()
        setConstraint()
    }

    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setUI()
        setConstraint()
    }

    private func setUI() {
        addSubview(titleLabel)
        addSubview(contentLabel)
    }

    private func setConstraint() {
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        contentLabel.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            titleLabel.topAnchor.constraint(equalTo: self.topAnchor, constant: 11),
            titleLabel.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 16),
            titleLabel.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: -11),

            contentLabel.topAnchor.constraint(equalTo: self.topAnchor, constant: 11),
            contentLabel.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -16),
            contentLabel.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: -11),
        ])
    }

    func setTitle(_ text: String) {
        titleLabel.text = text
    }

    func setContent(_ text: String) {
        contentLabel.text = text
    }

}
