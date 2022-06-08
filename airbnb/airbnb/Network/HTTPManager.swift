//
//  HTTPManager.swift
//  airbnb
//
//  Created by Bibi on 2022/06/01.
//

import Foundation
import os
import RxSwift

// HTTP 요청을 보내고, 그 결과를 받는 역할
final class HTTPManager {

    static func requestGET(url: String, complete: @escaping (Data) -> ()) {
      // complete @escaping  : 클로저가 바로 실행되지 않고, 조건에 해당될 때 클로저가 실행됨
        guard let validURL = URL(string: url) else { return }

        var urlRequest = URLRequest(url: validURL) // URL에 보내는 URLRequest 생성
        urlRequest.httpMethod = HTTPMethod.get.description

        URLSession.shared.dataTask(with: urlRequest) { data, urlResponse, error in
            // 비동기!! -> 서버에서 요청이 처리된 다음 실행되는 부분.
                        // dataTask(with request: URLRequest, completionHandler: @escaping (Data?, URLResponse?, Error?) -> Void) -> URLSessionDataTask
                        // completion handler의 내용은 모두 옵셔널로 넘어온다.
            guard let data = data else { return }
            guard let response = urlResponse as? HTTPURLResponse,
                                    (200..<300).contains(response.statusCode) else { // response를 HTTPURLResponse로 바꿨을 때 statusCode가 200번대(성공)이면 계속 진행
                if let response = urlResponse as? HTTPURLResponse {
                    os_log("%@", "\(response.statusCode)") // 아니라면 statusCode 출력
                }
                return
            }

            complete(data) // complete에 담겨 온 디코더 클로저에 data를 넘김
        }.resume() // 해당 task를 실행함
    }
    
    static func requestGetRx(url: String) -> Observable<Data> {
        return Observable.create { emitter in
            requestGET(url: url) { data in
                emitter.onNext(data)
                emitter.onCompleted()
            }
            return Disposables.create()
        }
    }

    //Post - encode된 Data를 매개변수로 받아옴
    static func requestPOST(url: String, encodingData: Data, complete: @escaping (Data) -> ()) {
        guard let validURL = URL(string: url) else { return }

        var urlRequest = URLRequest(url: validURL)
        urlRequest.httpMethod = HTTPMethod.post.description
        urlRequest.httpBody = encodingData // GET과 다르게 보낼 데이터를 httpBody에 넣어준다
        urlRequest.addValue("application/json", forHTTPHeaderField: "Content-Type")
        urlRequest.setValue("\(encodingData.count)", forHTTPHeaderField: "Content-Length")

        URLSession.shared.dataTask(with: urlRequest) { data, urlResponse, error in
            guard let data = data else { return }
            guard let response = urlResponse as? HTTPURLResponse, (200..<300).contains(response.statusCode) else {
                if let response = urlResponse as? HTTPURLResponse {
                    os_log("%@", "\(response.statusCode)")
                }
                return
            }

            complete(data)
        }.resume()
    }
    
    static func requestPostByFormData(url: String, key: String, value: String, complete: @escaping (Data) -> ()) {
        guard let validURL = URL(string: url) else { return }

        var urlRequest = URLRequest(url: validURL)
        urlRequest.httpMethod = HTTPMethod.post.description
        urlRequest.addValue("application/json", forHTTPHeaderField: "Content-Type")
        urlRequest.setValue("application/x-www-form-urlencoded; charset=utf-8", forHTTPHeaderField: "Content-Type")
        urlRequest.httpBody = "\(key)=\(value)".data(using: .utf8) // POST는 보낼 데이터를 Data로 httpBody에 넣어준다

        URLSession.shared.dataTask(with: urlRequest) { data, urlResponse, error in
            guard let data = data else { return }
            guard let response = urlResponse as? HTTPURLResponse, (200..<300).contains(response.statusCode) else {
                if let response = urlResponse as? HTTPURLResponse {
                    os_log("%@", "\(response.statusCode)")
                }
                return
            }
            complete(data)
        }.resume()
    }
    
    //Patch - Post와 비슷함
    static func requestPATCH(url: String, encodingData: Data, complete: @escaping (Data) -> ()) {
        guard let validURL = URL(string: url) else { return }

        var urlRequest = URLRequest(url: validURL)
        urlRequest.httpMethod = HTTPMethod.patch.description
        urlRequest.httpBody = encodingData
        urlRequest.addValue("application/json", forHTTPHeaderField: "Content-Type")
        urlRequest.setValue("\(encodingData.count)", forHTTPHeaderField: "Content-Length")

        URLSession.shared.dataTask(with: urlRequest) { data, response, error in
            guard let data = data else { return }
            guard let response = response as? HTTPURLResponse, (200..<300).contains(response.statusCode) else {
                if let response = response as? HTTPURLResponse{
                    os_log("%@", "\(response.statusCode)")
                }
                return
            }

            complete(data)
        }.resume()
    }

    // Delete
    static func requestDELETE(url: String, encodingData: Data, complete: @escaping (Data) -> ()) {
        guard let validURL = URL(string: url) else { return }
        var urlRequest = URLRequest(url: validURL)
        urlRequest.httpMethod = HTTPMethod.delete.description
        urlRequest.httpBody = encodingData
        urlRequest.addValue("application/json", forHTTPHeaderField: "Content-Type")

        URLSession.shared.dataTask(with: urlRequest) { data, response, error in
            guard let data = data else { return }
            guard let response = response as? HTTPURLResponse, (200..<300).contains(response.statusCode) else { return }

            complete(data)
        }.resume()
    }
}
