//
//  JSONConverter.swift
//  airbnb
//
//  Created by Bibi on 2022/06/01.
//

import Foundation

// JSON decoding(JSON->swift객체)과 encoding(swift객체->JSON) 담당
final class JSONConverter {
    
    static func decodeJsonArray<T: Codable>(data: Data) -> [T]? {
        // JSON 배열 디코더 : JSON Array를 [T] 타입으로 변환
        do {
            let result = try JSONDecoder().decode([T].self, from: data)
            return result
        } catch { // 에러 발생 시
            guard let error = error as? DecodingError else { return nil }
            // error : catch 내부에서 암시적으로 사용되는 에러를 나타내는 파라미터

            switch error { // 에러 유형에 따른 에러메시지 출력 가능
            case .dataCorrupted(let context):
                print(context.codingPath, context.debugDescription, context.underlyingError ?? "", separator: "\n")
                return nil
            default :
                return nil
            }
        }
    }

    static func decodeJson<T: Codable>(data: Data) -> T? {
        // JSON 객체 디코더 : JSON 객체를 T타입으로 변환
        do {
            let result = try JSONDecoder().decode(T.self, from: data) // T타입인 것 빼고 위 메서드와 동일
            return result
        } catch {
            guard let error = error as? DecodingError else { return nil }

            switch error {
            case .dataCorrupted(let context):
                print(context.codingPath, context.debugDescription, context.underlyingError ?? "", separator: "\n")
                return nil
            default :
                return nil
            }
        }
    }

    static func encodeJson<T: Codable>(param: T) -> Data? {
        // JSON 인코더 : T타입의 객체를 JSON으로 변환
        do {
            let result = try JSONEncoder().encode(param)
            return result
        } catch {
            return nil
        }
    }
}
