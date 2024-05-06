package com.wesleyem.smolurl.resource;

import com.wesleyem.smolurl.exceptions.ConflictingIdsGivenException;
import com.wesleyem.smolurl.model.SmolUrl;
import com.wesleyem.smolurl.repository.SmolUrlRepository;
import com.wesleyem.smolurl.service.SmolUrlService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class SmolUrlResource {
    private final SmolUrlService smolUrlService;

    @GetMapping("/smolUrls")
    public List<SmolUrl> getSmolUrls() {
        return smolUrlService.findAll();
    }

    @GetMapping("/smolUrl/{id}")
    public SmolUrl getWithId(@PathVariable("id") String id) {
        return smolUrlService.get(id);
    }

    @DeleteMapping("/smolUrl/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        smolUrlService.delete(id);
    }

    @PostMapping("/smolUrl")
    public SmolUrl create(@NotNull @RequestBody SmolUrl smol) {
        return smolUrlService.create(smol.getTargetUrl());
    }

    @PutMapping("/smolUrl/{id}")
    public SmolUrl update(@NotNull @PathVariable("id") String id, @NotNull @RequestBody SmolUrl smol) {
        if (!Objects.equals(id, smol.getId()))
            throw new ConflictingIdsGivenException(id + " and " + smol.getId() + " are not the same.");
        return smolUrlService.update(smol);
    }

}
