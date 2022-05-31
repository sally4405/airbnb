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
        label.font = UIFont.systemFont(ofSize: 20, weight: .bold)
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
    
    private let calendarView = UICollectionView(frame: .zero, collectionViewLayout: UICollectionViewFlowLayout())
    
    private let spacing: CGFloat = 10
    
    private let now = Date()
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
        
        setCalendarFirstDay()
        calculateCalendar()
        
        setYearMonthLabel()
        setPreviousButton()
        setNextButton()
        setCalendarView()
    }
    
    private func setCalendarFirstDay() {
        dateFormatter.dateFormat = "yyyy년 M월" // 월 표시 포맷 설정
        components.year = calendar.component(.year, from: now)
        components.month = calendar.component(.month, from: now)
        components.day = 1
    }
    
    private func calculateCalendar() { // 월별 일 수 계산
        let firstDayOfMonth = calendar.date(from: components)
        let firstWeekday = calendar.component(.weekday, from: firstDayOfMonth!) // 1 = 일요일, 7 = 토요일
        daysCountInMonth = calendar.range(of: .day, in: .month, for: firstDayOfMonth!)!.count
        weekdayAdding = 2 - firstWeekday
        
        self.yearMonthLabel.text = dateFormatter.string(from: firstDayOfMonth!)
        self.days.removeAll()
        
        for day in weekdayAdding...daysCountInMonth {
            if day < 1 { // 1보다 작을 경우는 빈칸
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
        ])
    }
    
    private func setPreviousButton() {
        self.view.addSubview(previousButton)
        previousButton.addTarget(self, action: #selector(previousButtonTouched), for: .touchUpInside)
        previousButton.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            previousButton.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor, constant: spacing),
            previousButton.trailingAnchor.constraint(equalTo: yearMonthLabel.leadingAnchor, constant: spacing * -1),
            previousButton.centerYAnchor.constraint(equalTo: yearMonthLabel.centerYAnchor)
        ])
    }
    
    private func setNextButton() {
        self.view.addSubview(nextButton)
        nextButton.addTarget(self, action: #selector(nextButtonTouched), for: .touchUpInside)
        nextButton.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            nextButton.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor, constant: spacing),
            nextButton.leadingAnchor.constraint(equalTo: yearMonthLabel.trailingAnchor, constant: spacing),
            nextButton.centerYAnchor.constraint(equalTo: yearMonthLabel.centerYAnchor)
        ])
    }
    
    private func setCalendarView() {
        self.view.addSubview(calendarView)
        calendarView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
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
        return 2 // 요일, 일
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
