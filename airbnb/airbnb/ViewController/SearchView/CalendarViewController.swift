//
//  CalenderController.swift
//  airbnb
//
//  Created by Bibi on 2022/05/30.
//

import UIKit

class CalendarViewController: UIViewController {
    
    private var yearMonthLabel: UILabel = {
        var label = UILabel()
        label.textAlignment = .center
        return label
    }()
    
    private var previousButton: UIButton = {
        var button = UIButton()
        button.setImage(UIImage(systemName: "chevron.left"), for: .normal)
        return button
    }()
    
    private var nextButton: UIButton = {
        var button = UIButton()
        button.setImage(UIImage(systemName: "chevron.right"), for: .normal)
        return button
    }()
    
    private let calendarView = UICollectionView(frame: .zero, collectionViewLayout: UICollectionViewFlowLayout())// CalendarView(frame: .zero, collectionViewLayout: UICollectionViewLayout())
    
    private let now = Date()
//    private let now = Calendar.current.date(byAdding: .day, value: 31, to: Date()) ?? Date()
    private var calendar = Calendar.current
    private let dateFormatter = DateFormatter()
    private var components = DateComponents()
    private var weeks: [String] = ["일", "월", "화", "수", "목", "금", "토"]
    private var days: [String] = []
    private var daysCountInMonth = 0 // 해당 월이 며칠까지 있는지
    private var weekdayAdding = 0 // 시작일
    private var daysCountWithSpace = 0
    private var weeksCountWithSpace = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.calendarView.dataSource = self
        self.calendarView.delegate = self
        self.calendarView.register(CalendarViewCell.self, forCellWithReuseIdentifier: CalendarViewCell.identifier)
        
        setView()
    }
    
    private func setView() {
        
        dateFormatter.dateFormat = "yyyy년 M월" // 월 표시 포맷 설정
        components.year = calendar.component(.year, from: now)
        components.month = calendar.component(.month, from: now)
        components.day = 1
        calculateCalendar()
        
        setYearMonthLabel()
        setPreviousButton()
        setNextButton()
        setCalendarView()
    }
    
    private func calculateCalendar() { // 월별 일 수 계산
        
        let firstDayOfMonth = calendar.date(from: components)
        let firstWeekday = calendar.component(.weekday, from: firstDayOfMonth!) // 요일. 1 = 일요일, 7 = 토요일
        daysCountInMonth = calendar.range(of: .day, in: .month, for: firstDayOfMonth!)!.count
        weekdayAdding = 2 - firstWeekday // 이 과정을 해주는 이유는 예를 들어 2020년 4월이라 하면 4월 1일은 수요일 즉, 수요일이 달의 첫날이 됩니다.  수요일은 component의 4 이므로 CollectionView에서 앞의 3일은 비울 필요가 있으므로 인덱스가 1일부터 시작할 수 있도록 해줍니다. 그래서 2 - 4 해서 -2부터 시작하게 되어  정확히 3일 후부터 1일이 시작하게 됩니다.
        //    1  일요일 2 - 1  -> 0번 인덱스부터 1일 시작
        //    2 월요일 2 - 2  -> 1번 인덱스부터 1일 시작
        //    3 화요일 2 - 3  -> 2번 인덱스부터 1일 시작
        //    4 수요일 2 - 4  -> 3번 인덱스부터 1일 시작
        //    5 목요일 2 - 5  -> 4번 인덱스부터 1일 시작
        //    6 금요일 2 - 6  -> 5번 인덱스부터 1일 시작
        //    7 토요일 2 - 7  -> 6번 인덱스부터 1일 시작
        
        
        self.yearMonthLabel.text = dateFormatter.string(from: firstDayOfMonth!)
        self.days.removeAll()
        
        for day in weekdayAdding...daysCountInMonth {
            if day < 1 { // 1보다 작을 경우는 비워줘야 하기 때문에 빈 값을 넣어준다.
                self.days.append("")
            } else {
                self.days.append(String(day))
            }
        }
        daysCountWithSpace = days.count
        if (daysCountWithSpace % 7) != 0 {
            weeksCountWithSpace = (daysCountWithSpace / 7) + 1
        } else {
            weeksCountWithSpace = daysCountWithSpace / 7
        }
    }
    
    private func setYearMonthLabel() {
        self.view.addSubview(yearMonthLabel)
        yearMonthLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            yearMonthLabel.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor),
            yearMonthLabel.centerXAnchor.constraint(equalTo: self.view.centerXAnchor),
//            yearMonthLabel.widthAnchor.constraint(equalToConstant: 300),
//            yearMonthLabel.heightAnchor.constraint(equalToConstant: 100)
        ])
        yearMonthLabel.backgroundColor = .red
    }
    
    private func setPreviousButton() {
        self.view.addSubview(previousButton)
        previousButton.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            previousButton.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor),
            previousButton.trailingAnchor.constraint(equalTo: yearMonthLabel.leadingAnchor),
        ])
        previousButton.addTarget(self, action: #selector(previousButtonTouched), for: .touchUpInside)
    }
    
    private func setNextButton() {
        self.view.addSubview(nextButton)
        nextButton.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            nextButton.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor),
//            nextButton.bottomAnchor.constraint(equalTo: calendarView.topAnchor),
            nextButton.leadingAnchor.constraint(equalTo: yearMonthLabel.trailingAnchor),
//            nextButton.centerXAnchor.constraint(equalTo: yearMonthLabel.centerXAnchor)
        ])
        nextButton.addTarget(self, action: #selector(nextButtonTouched), for: .touchUpInside)
    }
    
    private func setCalendarView() {
        self.view.addSubview(calendarView)
        calendarView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            //calendarView.widthAnchor.constraint(equalTo: self.view.widthAnchor),
            calendarView.topAnchor.constraint(equalTo: yearMonthLabel.bottomAnchor),
            calendarView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor),
            calendarView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor),
            calendarView.bottomAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.bottomAnchor),
//            calendarView.heightAnchor.constraint(equalTo: calendarView.widthAnchor)
        ])
    }
    
    @objc
    private func previousButtonTouched() {
        guard let currentMonth = components.month else {
            return
        }
        components.month = currentMonth - 1
        print(components.month)
        calculateCalendar()
        DispatchQueue.main.async {
            self.calendarView.reloadData()
        }
    }
    
    @objc
    private func nextButtonTouched() {
        guard let currentMonth = components.month else {
            return
        }
        components.month = currentMonth + 1
        calculateCalendar()
        calendarView.reloadData()
    }
}

extension CalendarViewController: UICollectionViewDataSource {
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 2 // 요일 + 일
//        return 1 + weeksCountWithSpace // 요일 + 주
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
//        return 7
        switch section {
        case 0:
            return 7 // 요일의 수는 고정
        default:
            return self.days.count // 일의 수
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: CalendarViewCell.identifier, for: indexPath) as? CalendarViewCell else {
            return UICollectionViewCell()
        }
        
//        cell.setDateLabelText(days[indexPath.item]) // 일
        
        switch indexPath.section {
        case 0:
            cell.setDateLabelText(weeks[indexPath.row]) // 요일
        default:
            cell.setDateLabelText(days[indexPath.row]) // 일
        }
        
        if indexPath.row % 7 == 0 { // 일요일
            cell.setDateLabelColor(.red)
        } else if indexPath.row % 7 == 6 { // 토요일
            cell.setDateLabelColor(.blue)
        } else { // 평일
            cell.setDateLabelColor(.black)
        }
//        cell.backgroundColor = .systemPink
        return cell
    }
}

extension CalendarViewController: UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let widthSize: CGFloat = self.view.bounds.size.width
        let cellSize : CGFloat = widthSize / 9
        return CGSize(width: cellSize, height: cellSize)
    }
}
