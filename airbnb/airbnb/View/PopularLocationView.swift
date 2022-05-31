import UIKit

class PopularLocationView: UIView {

    private var locationList: [[String]] = [["서울", "차로 30분 거리"], ["광주", "차로 4시간 거리"]]
    private var imageList: [String] = ["seoul", "gwangju"]

    private lazy var tableView: UITableView = {
        var table = UITableView()
        table.delegate = self
        table.dataSource = self
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

extension PopularLocationView: UITableViewDelegate, UITableViewDataSource {

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return locationList.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: LocationTableViewCell.identifier, for: indexPath) as? LocationTableViewCell else { return UITableViewCell() }
        cell.selectionStyle = .none
        let location = locationList[indexPath.row]
        cell.setTitle(location[0])
        cell.setDistance(location[1])
        cell.setImage(UIImage(named: imageList[indexPath.row]) ?? UIImage())
        return cell
    }

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
