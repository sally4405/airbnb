import UIKit

class CalendarViewCell: UICollectionViewCell {
    
    static let identifier = "CalendarViewCell"
    
    private var dateLabel: UILabel = {
        var label = UILabel()
        label.text = "date"
        return label
    }()
    
    func setDateLabelText(_ text: String) {
        dateLabel.text = text
    }
    
    func setDateLabelColor(_ color: UIColor) {
        dateLabel.textColor = color
    }

    override init(frame: CGRect) {
        super.init(frame: frame)
        setUp()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setUp()
    }
    
    private func setUp() {
        setDateLabel()
    }
    
    private func setDateLabel() {
        self.addSubview(dateLabel)
        dateLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            dateLabel.topAnchor.constraint(equalTo: self.topAnchor),
            dateLabel.bottomAnchor.constraint(equalTo: self.bottomAnchor),
            dateLabel.leadingAnchor.constraint(equalTo: self.leadingAnchor),
            dateLabel.trailingAnchor.constraint(equalTo: self.trailingAnchor),
        ])
    }
}
