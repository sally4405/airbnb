import UIKit
import RxSwift
import RxCocoa

class PopularLocationView: UIView {

    private var viewModel = PopularLocationViewModel()
    private var disposeBag = DisposeBag()

    private lazy var tableView: UITableView = {
        var table = UITableView()
        table.delegate = self
        table.register(LocationTableViewCell.self, forCellReuseIdentifier: LocationTableViewCell.identifier)
        table.separatorStyle = .none
        return table
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
        backgroundColor = .systemBackground
        viewModel.popularLocation
            .observe(on: MainScheduler.instance)
            .bind(to: tableView.rx.items(cellIdentifier: LocationTableViewCell.identifier, cellType: LocationTableViewCell.self)) { index, item, cell in
                cell.selectionStyle = .none
                cell.setTitle(item.name)
                cell.setDistance(item.distance)
                cell.setImage(UIImage())
            }
            .disposed(by: disposeBag)
        
        setTableView()
    }

    private func setTableView() {
        addSubview(tableView)
        tableView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            tableView.topAnchor.constraint(equalTo: self.topAnchor),
            tableView.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 16),
            tableView.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -16),
            tableView.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: -40)
        ])
    }

}

extension PopularLocationView: UITableViewDelegate {

    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        let view = UIView(frame: .init(x: 0, y: 0, width: tableView.frame.width, height: 32))
        let label = UILabel(frame: .init(x: 0, y: 0, width: view.frame.width, height: view.frame.height))
        label.text = "근처의 인기 여행지"
        label.font = UIFont.systemFont(ofSize: 17, weight: .bold)
        view.addSubview(label)

        return view
    }

    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 32
    }

}
