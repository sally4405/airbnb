import UIKit

class SearchTableView: UIView {

    private let titleList = ["위치", "체크인/체크아웃", "요금", "인원"]
    private var contentList: [String] = []

    private lazy var tableView: UITableView = {
        var table = UITableView()
        table.delegate = self
        table.dataSource = self
        table.register(SearchTableViewCell.self, forCellReuseIdentifier: SearchTableViewCell.identifier)
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
        setTableView()
    }

    private func setTableView() {
        addSubview(tableView)
        tableView.isScrollEnabled = false

        tableView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            tableView.topAnchor.constraint(equalTo: self.topAnchor),
            tableView.leadingAnchor.constraint(equalTo: self.leadingAnchor),
            tableView.trailingAnchor.constraint(equalTo: self.trailingAnchor),
            tableView.bottomAnchor.constraint(equalTo: self.bottomAnchor)
        ])
    }

    func setContentList(_ list: [String]) {
        contentList = list
        tableView.reloadData()
    }

}

extension SearchTableView: UITableViewDelegate, UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return titleList.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: SearchTableViewCell.identifier, for: indexPath) as? SearchTableViewCell else { return UITableViewCell() }
        cell.selectionStyle = .none
        cell.setTitle(titleList[indexPath.row])
        cell.setContent(contentList[indexPath.row])
        return cell
    }

    func tableView(_ tableView: UITableView, viewForFooterInSection section: Int) -> UIView? {
        return SearchTableFooterView()
    }

}
