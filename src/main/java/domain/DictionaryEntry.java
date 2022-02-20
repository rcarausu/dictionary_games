package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class DictionaryEntry {
    @NonNull
    private String word;
    @NonNull
    private String definitions;
}
