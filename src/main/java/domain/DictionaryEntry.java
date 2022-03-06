package domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class DictionaryEntry {
    @NonNull
    private String word;
    @NonNull
    private String definitions;
}
