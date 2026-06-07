param(
    [string]$Message = "Update"
)

# In das Projektverzeichnis wechseln
Set-Location "$PSScriptRoot\.."

Write-Host "Working Directory:"
Get-Location

$status = git status --porcelain

if ($status) {
    git add .
    git commit -m $Message
    git push

    if ($LASTEXITCODE -ne 0) {
        throw "Git Push fehlgeschlagen"
    }
} else {
    Write-Host "Keine Änderungen gefunden."
}

ssh bro@192.168.0.128 "~/apollon-core/deploy-backend.sh"