package com.sparta.personal_homework_spring.diary.controller;

import com.sparta.personal_homework_spring.diary.dto.DiaryRequestDto;
import com.sparta.personal_homework_spring.diary.dto.DiaryResponseDto;
import com.sparta.personal_homework_spring.diary.entity.Diary;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DiaryController {

    private final Map<Long, Diary> diaryList = new HashMap<>();

    //데이터 저장
    @PostMapping("/diary")
    @ResponseBody
    public DiaryResponseDto createDiary(@RequestBody DiaryRequestDto requestDto) {
        // RequestDto -> Entity
        Diary diary = new Diary(requestDto);
        // Diary Max Id check
        Long maxId = diaryList.size() > 0 ? Collections.max(diaryList.keySet()) + 1 : 1;
        diary.setId(maxId);
        //DB 저장
        diaryList.put(diary.getId(), diary);
        //entity -> responseDto
        DiaryResponseDto diaryResponseDto = new DiaryResponseDto(diary);

        return diaryResponseDto;
    }

    //날짜로 선택조회
    @GetMapping("/diary/{date}")
    @ResponseBody
    public List<DiaryResponseDto> getDiaryByDate(@PathVariable int date) {
        List<DiaryResponseDto> selectedDiary = diaryList.values().stream()
                .filter(diary -> diary.getDate() == date)
                .map(DiaryResponseDto::new)
                .collect(Collectors.toList());

        return selectedDiary;
    }

    //전체조회
    @GetMapping("/diaries")
    @ResponseBody
    public List<DiaryResponseDto> getDiary() {
        //map to list
        List<DiaryResponseDto> responseList = diaryList.values().stream()
                .map(DiaryResponseDto::new).toList();

        return responseList;
    }

    //데이터 수정
    @PutMapping("/diary/{id}/{password}")
    @ResponseBody
    public Long updateDiary(@PathVariable Long id, @PathVariable int password, @RequestBody DiaryRequestDto requestDto) {
        //해당 메모가 DB에 존재하는지 확인
        if (diaryList.containsKey(id)) {
            //해당 메모 가져오기
            Diary diary = diaryList.get(id);
            if (diary.getPassword() == password) {
                //일정 수정
                diary.update(requestDto);
            } else {
                throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
            }

            return diary.getId();

        } else {
            throw new IllegalArgumentException("선택한 일정은 존재하지 않습니다.");
        }
    }

    //데이터 삭제
    @DeleteMapping("/diary/{id}/{password}")
    @ResponseBody
    public Long deletDiary(@PathVariable Long id, @PathVariable int password) {
        //해당 메모가 DB에 존재하는지 확인
        if (diaryList.containsKey(id)) {
            Diary diary = diaryList.get(id);
            if (diary.getPassword() == password) {
                //해당 메모 삭제하기
                diaryList.remove(id);
            }
            return id;
        } else {
            throw new IllegalArgumentException("선택한 일정은 존재하지 않습니다.");
        }
    }
}
